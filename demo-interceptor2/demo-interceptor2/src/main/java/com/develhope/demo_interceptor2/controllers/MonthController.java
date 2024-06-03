package com.develhope.demo_interceptor2.controllers;

import com.develhope.demo_interceptor2.entities.Month;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/months")
public class MonthController {

    public Month getMonth(@RequestAttribute("month") Month month){
        return month;
    }


}
