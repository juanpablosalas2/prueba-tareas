package com.example.pruebatareas.domain.model.exceptions;

public class TaskException extends RuntimeException{

    public TaskException(String message) {
        super(message);
    }

    public TaskException(String message, Throwable cause) {
        super(message, cause);
    }
}
