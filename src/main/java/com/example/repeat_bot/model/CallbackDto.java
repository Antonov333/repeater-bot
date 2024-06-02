package com.example.repeat_bot.model;

import lombok.Data;
import lombok.ToString;

/**
 * Model of response from VK sent to callback endpoint
 */
@Data
@ToString
public class CallbackDto {
    private String type;
    private Long group_id;
    private String event_id;
    private String v;
    private ObjectWithMessage object;
}

/*
"object": { "message": { "date": 1717337972, "from_id": 864667479, "id": 1, "out": 0, "version": 10000002,
"attachments": [],
"conversation_message_id": 1,
"fwd_messages": [],
"important": false, "is_hidden": false, "peer_id": 864667479, "random_id": 0,
"text": "Привет!", "is_mentioned_user": false }*/
