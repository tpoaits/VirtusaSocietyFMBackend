package com.virtusa.societyfm.exceptions;

public class UserAlreadyRegisterException extends  RuntimeException {
    String message;

    public UserAlreadyRegisterException(String message) {
        this.message = message;
    }
}
