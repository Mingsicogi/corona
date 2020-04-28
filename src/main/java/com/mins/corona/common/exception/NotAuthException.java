package com.mins.corona.common.exception;

public class NotAuthException extends RuntimeException {

    public NotAuthException(String message) {
        super(message);
    }

    public NotAuthException() {
        super();
    }
}
