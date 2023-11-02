package com.techcompany.backend.excepction;

public class EmployeeDeleteException extends RuntimeException {
    public EmployeeDeleteException(String message) {
        super(message);
    }

    public EmployeeDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
