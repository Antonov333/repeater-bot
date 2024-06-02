package com.example.repeat_bot.model;

import lombok.Data;

/**
 * Model of {@code client_info} field in {@link ObjectWithMessage}
 */
@Data
public class ClientInfo {
    private String[] button_actions;
    private Boolean keyboard;
    private Boolean inline_keyboard;
    private Boolean carousel;
    private Long lang_id;
}
