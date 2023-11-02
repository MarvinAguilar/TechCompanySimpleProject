package com.techcompany.backend.excepction;

public class EmployeeSaveException extends RuntimeException  {
    public EmployeeSaveException(String message) {
        super(message);
    }

    public EmployeeSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
