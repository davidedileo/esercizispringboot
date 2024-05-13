package com.develhope.swaggerexample.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

//indirizzo locale: http://localhost:8080/swagger-ui/index.html
//documentazione: http://localhost:8080/v3/api-docs
@RestController
@RequestMapping("/v1")
public class NameController {

    @Operation (summary = "getName", description = "Restituisce il nome inserito come input")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Il nome viene restituito con successo"),
            @ApiResponse(responseCode = "404", description = "Risorsa non trovata")
    })
    @GetMapping(path = "/{name}")
    public String getName(@PathVariable String name, @Parameter(name = "nome", description = "Nome inserito", example = "Davide") String nome ){
        return name;
    }


    @Operation (summary = "reverseName", description = "Restituisce il nome inserito come input, ma invertito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Il nome viene restituito con successo"),
            @ApiResponse(responseCode = "404", description = "Risorsa non trovata")
    })
    @PostMapping(path = "/{name}")
    public String reverseName(@PathVariable String name, @Parameter(name = "nome", description = "Nome inserito", example = "Davide") String nome) {
        StringBuilder reversedName = new StringBuilder(name).reverse();
        return reversedName.toString();
    }
}
