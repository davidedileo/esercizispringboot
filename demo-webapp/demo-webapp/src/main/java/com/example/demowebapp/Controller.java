package com.example.demowebapp;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class Controller {

    @GetMapping(path = "/ciao")
    public String ciao(@RequestParam String nome, @RequestParam String provincia){
        return "Ciao " + nome + ", com'è il tempo in " + provincia + "?";
    }
}
