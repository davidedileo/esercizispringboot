package com.develhope.demo_deploy02.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
@RestController
public class SumController {
    @GetMapping("/sum")
    public int getRandomSum(){
        Random random = new Random();
        int num1 = random.nextInt();
        int num2 = random.nextInt();
        return num1 + num2;
    }

}
