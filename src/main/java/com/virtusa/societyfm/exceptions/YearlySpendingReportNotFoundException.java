package com.virtusa.societyfm.exceptions;

public class YearlySpendingReportNotFoundException extends RuntimeException {

    public YearlySpendingReportNotFoundException() {
        super();
    }

    public YearlySpendingReportNotFoundException(String message) {
        super(message);
    }

    public YearlySpendingReportNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public YearlySpendingReportNotFoundException(Throwable cause) {
        super(cause);
    }
}
