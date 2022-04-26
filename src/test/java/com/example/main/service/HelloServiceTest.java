package com.example.main.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = HelloService.class)
class HelloServiceTest {

    @Test
    void hello_test() {
        HelloService helloService = new HelloService();
        ResponseEntity<Object> responseEntity = helloService.hello();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}