package com.virtusa.societyfm.exceptions;

public class OutwardPaymentNotFoundException extends RuntimeException {

    public OutwardPaymentNotFoundException() {
        super();
    }

    public OutwardPaymentNotFoundException(String message) {
        super(message);
    }

    public OutwardPaymentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutwardPaymentNotFoundException(Throwable cause) {
        super(cause);
    }
}
