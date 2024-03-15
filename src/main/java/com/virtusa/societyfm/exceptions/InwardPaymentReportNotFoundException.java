package com.virtusa.societyfm.exceptions;

public class InwardPaymentReportNotFoundException extends RuntimeException {

    public InwardPaymentReportNotFoundException() {
        super();
    }

    public InwardPaymentReportNotFoundException(String message) {
        super(message);
    }

    public InwardPaymentReportNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InwardPaymentReportNotFoundException(Throwable cause) {
        super(cause);
    }
}
