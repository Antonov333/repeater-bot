package com.example.repeat_bot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepeaterController {

    @GetMapping
    public String home() {
        return "Repeater Controller Home";
    }
}
