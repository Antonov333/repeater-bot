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

    /**
     * Эндпойнт для проверки работы контроллера
     *
     * @return Приветствие
     */
    @GetMapping
    public String home() {
        return repeaterService.home();
    }

    /**
     * Обрабобтка сообщений VK:
     * <br>Получение подтверждения регистрации callback адреса
     * <br>Обработка информации о событиях, отправляемая порталом после подтверждения адреса callback
     * @param callbackDto ответ VK
     * @return значение, заданное VK для подтверждения подключения сервера
     */
    @PostMapping
    public String callbackReceiver(@RequestBody CallbackDto callbackDto) {
        return repeaterService.callBackProcessor(callbackDto);
    }
}
