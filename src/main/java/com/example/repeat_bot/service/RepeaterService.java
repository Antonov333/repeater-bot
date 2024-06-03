package com.example.repeat_bot.service;

import com.example.repeat_bot.model.CallbackDto;
import com.example.repeat_bot.model.PortalResponseDto;
import com.example.repeat_bot.model.UrlForPostingMessageTemplate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RepeaterService {
    @Value("${return_on.confirmation}")
    String RESPONSE_ON_CONFIRMATION;

    @Value("${access.token}")
    String ACCESS_TOKEN;

    @Value("${portal.location}")
    String PORTAL_LOCATION;

    @Value("${callback.server.confirmed}")
    String confirmationReceived;

    @Value("${api.version}")
    String apiVersion;

    private final Logger logger = LoggerFactory.getLogger(RepeaterService.class);

    /**
     * This method is invoked to process call back from VK
     * @param callbackDto {@link CallbackDto} response sent from VK
     * @return callback confirmation String as required by VK if callback not confirmed, while "ok" upon callBackProcessor completed
     */
    public String callBackProcessor(CallbackDto callbackDto) {
        String responseString;

        responseString = response(callbackDto);

        logger.info("Received callBack object as follows: {}",
                callbackDto);

        if ("ok".equals(responseString)) {
            repeatUserMessageInChat(callbackDto);
        }

        logger.info("{} to return",
                responseString);
        return responseString;
    }

    private void repeatUserMessageInChat(CallbackDto callbackDto) {

        long senderId = 0;
        long peerId = 0;
        String messageText = "";
        // Parse callback dto for sender id and message text
        if (null != callbackDto.getObject().getMessage()) {
            senderId = callbackDto.getObject().getMessage().getFrom_id();
            peerId = callbackDto.getObject().getMessage().getPeer_id();
            messageText = callbackDto.getObject().getMessage().getText();
        }

        if (messageText.isEmpty()) {
            return;
        }

        messageText = StringUtils.replaceIgnoreCase(messageText, " ", "+");

        String url = UrlForPostingMessageTemplate.builder()
                .portalHome(PORTAL_LOCATION)
                .method("messages.send")
                .userId(senderId)
                .peerId(peerId)
                .accessToken(ACCESS_TOKEN)
                .v(apiVersion)
                .messageString(messageText)
                .build()
                .getAsString();

        logger.info("Assembled url: {}", url);

        Mono<PortalResponseDto> portalResponseDtoMono =
                WebClient.create(url).get().retrieve().bodyToMono(PortalResponseDto.class);

        PortalResponseDto portalResponseDto = portalResponseDtoMono.share().block();

        logger.info("Received response from portal: {}", portalResponseDto);
    }

    /**
     * To obtain VK callBack server callbackReceiver it is required to return some string specified by portal.
     * Upon callBack callbackReceiver completed "ok" must be returned to any request from VK
     *
     * @param callbackDto value from CallbackDto {@link CallbackDto}
     * @return String required by VK to complete callBackProcessor or "ok" upon callBackProcessor completed<br>
     * In case of using permanent call back URL return value is be always "ok" upon call back server URL is confirmed by VK
     */
    String response(CallbackDto callbackDto) {
        String response = RESPONSE_ON_CONFIRMATION;
        String ok = "ok";
        if (ok.equals(confirmationReceived)) {
            response = ok;
        }
        if (callbackDto.getGroup_id() != null) {
            confirmationReceived = ok;
        }
        return response;
    }

    /**
     * Endpoint to make sure backend application is running
     * @return welcome message
     */
    public String home() {
        logger.info("Repeater Controller Home invoked");
        return "Welcome to Repeater Controller Home!";
    }

}
