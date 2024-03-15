package com.virtusa.societyfm.exceptions;

public class SocietyUserNotFoundException extends RuntimeException {

    public SocietyUserNotFoundException() {
        super();
    }

    public SocietyUserNotFoundException(String message) {
        super(message);
    }

    public SocietyUserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SocietyUserNotFoundException(Throwable cause) {
        super(cause);
    }
}
