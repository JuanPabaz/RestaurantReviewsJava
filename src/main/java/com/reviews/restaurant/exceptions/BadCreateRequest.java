package com.reviews.restaurant.exceptions;

public class BadCreateRequest extends RuntimeException {

    public BadCreateRequest(String message) {
        super(message);
    }
}
