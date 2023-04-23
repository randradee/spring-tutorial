package com.randradee.springtutorial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TutorialController {

    @GetMapping
    public String helloWorld(){
        return "Hello World! aaa";
    }
}
