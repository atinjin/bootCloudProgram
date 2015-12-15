package com.ninza.web;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String helloSpringBoot() {
        return "Hello, Spring Boot!";
    }

}