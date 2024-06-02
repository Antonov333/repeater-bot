package com.example.repeat_bot.model;

import lombok.Data;

/**
 * Model of data provided from VK in "object" field of DTO pushed via callback endpoint
 */
@Data
public class ObjectWithMessage {
    private MessageField message;
    private ClientInfo clientInfo;
}
