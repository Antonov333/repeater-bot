package com.example.repeat_bot.model;

import lombok.Data;


/**
 *
 */
@Data
public class MessageField {
    private Long date;
    private Long from;
    private Long id;
    private Long out;
    private Long version;
    private String[] attachments;
    private Long conversation_message_id;
    private String[] fwd_messages;
    private Boolean important;
    private Boolean is_hidden;
    private Long peer_id;
    private Long random_id;
    private String text;
    private Boolean is_mentioned_user;

}
