package com.virtusa.societyfm.exceptions;

public class EmailTrackNotFoundException extends RuntimeException {

    public EmailTrackNotFoundException() {
        super();
    }

    public EmailTrackNotFoundException(String message) {
        super(message);
    }

    public EmailTrackNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailTrackNotFoundException(Throwable cause) {
        super(cause);
    }
}
