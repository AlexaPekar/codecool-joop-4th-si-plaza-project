package com.codecool.plaza.api;

public class OutOfStockException extends ShopException {

    OutOfStockException(String message) {
        super(message);
    }
}
