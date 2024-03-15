package com.virtusa.societyfm.exceptions;

public class DelayedPaymentReportNotFoundException extends RuntimeException {

    public DelayedPaymentReportNotFoundException() {
        super();
    }

    public DelayedPaymentReportNotFoundException(String message) {
        super(message);
    }

    public DelayedPaymentReportNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DelayedPaymentReportNotFoundException(Throwable cause) {
        super(cause);
    }
}
