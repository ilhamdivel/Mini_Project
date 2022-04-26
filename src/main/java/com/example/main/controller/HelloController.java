package com.example.main.controller;

import com.example.main.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mini-project")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping(value = "")
    public ResponseEntity<Object> hello() {
        return helloService.hello();
    }
}
