package com.develhope.demowebapp3;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class NameController {

    @GetMapping(path = "/{name}")
    public String getName(@PathVariable String name){
        return name;
    }

    @PostMapping(path = "/{name}")
    public String reverseNAme(@PathVariable String name) {
        StringBuilder reversedName = new StringBuilder(name).reverse();
        return reversedName.toString();
    }
}
