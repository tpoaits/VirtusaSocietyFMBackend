package com.virtusa.societyfm.exceptions;

public class AuthenticationFailedException extends  RuntimeException {
    String message;

    public AuthenticationFailedException(String message) {
        this.message = message;
    }
}
