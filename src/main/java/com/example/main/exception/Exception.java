package com.example.main.exception;

import com.example.main.enums.ResultEnum;

public class Exception extends RuntimeException{
    private Integer code;

    public Exception(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public Exception(Integer code, String message){
        super(message);
        this.code = code;
    }
}
