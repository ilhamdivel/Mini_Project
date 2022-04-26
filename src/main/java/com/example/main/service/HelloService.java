package com.example.main.service;

import com.example.main.model.dto.HelloDto;
import com.example.main.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.example.main.constant.AppConstant.KEY_SUCCESS;
@Slf4j
@Service
public class HelloService {
    public ResponseEntity<Object> hello() {
        HelloDto helloDto = HelloDto.builder()
                .content("Hello World")
                .build();
        return ResponseUtil.build(helloDto, KEY_SUCCESS, HttpStatus.OK);
    }



}
