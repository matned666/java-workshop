package eu.mrndesign.matned.javaworkshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/")
    public String getStartTest(){
        return "Hello server";
    }
}
