package com.example.repeat_bot.service;

import com.example.repeat_bot.model.CallbackDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RepeaterService {
    @Value("${return_on.confirmation}")
    String RESPONSE_ON_CONFIRMATION;

    @Value("{access.token}")
    String ACCESS_TOKEN;

    private boolean confirmationReceived = false;

    private final Logger logger = LoggerFactory.getLogger(RepeaterService.class);

    /**
     * This method is invoked to fulfill confirmation as reuired by VK
     *
     * @param callbackDto {@link CallbackDto} request body sent from VK
     * @return String with code from VK (must be applied to application.properties
     * manually or "ok" upon confirmation completed
     */
    public String confirmation(CallbackDto callbackDto) {
        String responseString;

        responseString = response(callbackDto);

        logger.info("Received confirmation as follows: {}, returned: {}",
                callbackDto,
                responseString);

        return responseString;
    }

    /**
     * To complete VK confirmation it is required to return some string specified by portal.
     * Upon confirmation completed "ok" must be returned to any request from VK side
     *
     * @param callbackDto value from CallbackDto {@link CallbackDto}
     * @return String required by VK to complete confirmation or "ok" upon confirmation completed<br>
     * In case of using permanent call back URL return value is be always "ok" upon call back server URL is confirmed by VK
     */
    String response(CallbackDto callbackDto) {
        String response = RESPONSE_ON_CONFIRMATION;
        if (confirmationReceived) {
            response = "ok";
        }
        if (callbackDto.getGroup_id() != null) {
            confirmationReceived = true;
        }
        return response;
    }

    /**
     * Endpoint to make sure backend application is running
     *
     * @return
     */
    public String home() {
        logger.info("Repeater Controller Home invoked");
        return "Repeater Controller Home";
    }

}
