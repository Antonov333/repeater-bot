package com.example.repeat_bot.model;

import lombok.Data;
import lombok.ToString;

/**
 * VK response upon successful publishing of message
 */
@Data
@ToString
public class PortalResponseDto {
    private long cmid;
    private long message_id;
}
