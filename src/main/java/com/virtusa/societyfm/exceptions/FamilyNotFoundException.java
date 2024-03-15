package com.virtusa.societyfm.exceptions;

public class FamilyNotFoundException extends RuntimeException {

    public FamilyNotFoundException() {
        super();
    }

    public FamilyNotFoundException(String message) {
        super(message);
    }

    public FamilyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FamilyNotFoundException(Throwable cause) {
        super(cause);
    }
}
