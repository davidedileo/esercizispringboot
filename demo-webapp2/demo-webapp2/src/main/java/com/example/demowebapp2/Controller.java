package com.example.demowebapp2;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2")
public class Controller {

    @GetMapping(path = "/ciao/{provincia}")
    public User ciao(@RequestParam String nome,
                     @PathVariable String provincia){
        String messaggio = "Ciao " + nome + ", com'è il tempo in " + provincia + "?";
        return new User(nome, provincia, messaggio);
    }
}
