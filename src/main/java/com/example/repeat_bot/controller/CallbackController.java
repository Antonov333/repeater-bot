package com.example.repeat_bot.controller;

import com.example.repeat_bot.model.CallbackDto;
import com.example.repeat_bot.service.RepeaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/for-callback")
public class CallbackController {

    private final RepeaterService repeaterService;

    @GetMapping
    public String home() {
        return repeaterService.home();
    }

    @PostMapping
    public String confirmation(@RequestBody CallbackDto callbackDto) {
        return repeaterService.confirmation(callbackDto);
    }
}
