package com.amazons3.amazon.exceptions;

public class ErrorReadingException extends Exception {

    String message;

    public ErrorReadingException(String message) {
        super(message);
    }
}
