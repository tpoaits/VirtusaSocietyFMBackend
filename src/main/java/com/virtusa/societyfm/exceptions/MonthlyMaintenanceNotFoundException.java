package com.virtusa.societyfm.exceptions;

public class MonthlyMaintenanceNotFoundException extends RuntimeException {

    public MonthlyMaintenanceNotFoundException() {
        super();
    }

    public MonthlyMaintenanceNotFoundException(String message) {
        super(message);
    }

    public MonthlyMaintenanceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MonthlyMaintenanceNotFoundException(Throwable cause) {
        super(cause);
    }
}
