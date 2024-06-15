package com.develhope.environment_variables.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final Environment environment;


    @GetMapping
    public String getMessage(){
        String authCode = environment.getProperty("app.authCode");
        String devName = environment.getProperty("app.devName");
        return String.format("Hello from %s! The auth code is %s.", devName, authCode);
    }
}
