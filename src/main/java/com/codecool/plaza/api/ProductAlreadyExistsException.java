package com.codecool.plaza.api;

public class ProductAlreadyExistsException extends ShopException {

    ProductAlreadyExistsException(String message) {
        super(message);
    }
}
