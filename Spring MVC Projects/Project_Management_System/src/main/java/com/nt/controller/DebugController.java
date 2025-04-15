package com.nt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/debug")
public class DebugController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/beans")
    public String[] getAllBeans() {
        return context.getBeanDefinitionNames();
    }
}