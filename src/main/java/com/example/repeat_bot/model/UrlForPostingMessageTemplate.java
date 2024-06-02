package com.example.repeat_bot.model;

import lombok.Builder;
import lombok.Data;

/**
 * Модель запроса для публикации сообщения
 */
@Data
@Builder
public class UrlForPostingMessageTemplate {
    private String portalHome;
    private String method;
    private String accessToken;
    private long userId;
    private long peerId;
    private String messageString;
    private String v;
    private long randomId;

    public String getAsString() {
        return portalHome + "/" + "method/" + method + "?user_id=" + userId + "&peer_id=" + peerId
                + "&message=" + messageString + "&access_token=" + accessToken + "&v=" + v + "&random_id=" + randomId;
    }
}


