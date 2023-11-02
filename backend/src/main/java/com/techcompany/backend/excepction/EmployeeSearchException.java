package com.techcompany.backend.excepction;

public class EmployeeSearchException extends RuntimeException {
    public EmployeeSearchException(String message) {
        super(message);
    }

    public EmployeeSearchException(String message, Throwable cause) {
        super(message, cause);
    }
}
