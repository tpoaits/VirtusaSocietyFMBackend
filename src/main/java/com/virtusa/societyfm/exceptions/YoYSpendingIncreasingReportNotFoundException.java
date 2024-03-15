package com.virtusa.societyfm.exceptions;

public class YoYSpendingIncreasingReportNotFoundException extends RuntimeException {

    public YoYSpendingIncreasingReportNotFoundException() {
        super();
    }

    public YoYSpendingIncreasingReportNotFoundException(String message) {
        super(message);
    }

    public YoYSpendingIncreasingReportNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public YoYSpendingIncreasingReportNotFoundException(Throwable cause) {
        super(cause);
    }
}
