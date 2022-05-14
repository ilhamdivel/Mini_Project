package com.example.main.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    USER_NOT_FOUND(60,"User is not exist!"),

    PRODUCT_NOT_EXIST(50, "Product does not exist!"),
    PRODUCT_NOT_ENOUGH(51, "Not enough products in stock!"),
    PRODUCT_STATUS_ERROR(52, "Status is incorrect!"),
    PRODUCT_OFF_SALE(53,"Product is off sale!"),

    ORDER_NOT_FOUND(40, "Order is not exist!"),
    ORDER_STATUS_ERROR(41, "Status is not correct"),

    VALID_ERROR(30, "Wrong information"),

    CATEGORY_NOT_FOUND(20,"Category not found"),
    ;
    private final Integer code;
    private final String message;

    ResultEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
