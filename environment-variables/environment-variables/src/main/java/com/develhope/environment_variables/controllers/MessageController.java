package com.develhope.environment_variables.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Value("${app.authCode}")
    private String authCode;

    @Value("${app.devName}")
    private String devName;

    @GetMapping
    public String getMessage(){
        return String.format("Hello from %s! Your auth code is %s.", devName, authCode);
    }
}
