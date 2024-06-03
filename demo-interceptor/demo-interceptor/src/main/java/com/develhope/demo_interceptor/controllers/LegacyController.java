package com.develhope.demo_interceptor.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/legacy")
public class LegacyController {

    @GetMapping
    public ResponseEntity<String> getMessage(){
        return ResponseEntity.ok("This is just old code");
    }
}
