package com.virtusa.societyfm.exceptions;

public class RecordNotFoundException extends  RuntimeException {
    String message;

    public RecordNotFoundException(String message) {
        this.message = message;
    }
}
