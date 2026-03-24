package com.example.SecureProxy.API.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class health {
    @GetMapping
    public String getHealth(){
        return  " hey all good till up , im still alive , rest you figure it out ";
    }
}
