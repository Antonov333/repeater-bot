package com.example.repeat_bot;

import com.example.repeat_bot.model.UrlForPostingMessageTemplate;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
public class RepeaterServiceTest {

    @Test
    void urlTemplateTest() {

        UrlForPostingMessageTemplate template = UrlForPostingMessageTemplate.builder()
                .portalHome("https://portal.home")
                .userId(1L)
                .method("method")
                .accessToken("accessToken")
                .peerId(2L)
                .messageString("Hi")
                .v("5.236")
                .build();

        System.out.println(template.getAsString());
    }

}
