package com.develhope.demo_deploy.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DevNameController {

    @Value("${devName}")
    private String devName;


    @GetMapping("/devName")
    public String devName(){
        return devName;
    }


}
