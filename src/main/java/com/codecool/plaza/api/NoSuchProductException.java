package com.codecool.plaza.api;

public class NoSuchProductException extends ShopException {

    NoSuchProductException(String message) {
        super(message);
    }
}
