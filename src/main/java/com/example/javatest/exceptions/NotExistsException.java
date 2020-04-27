package com.example.javatest.exceptions;

/**
 * NotExistsException
 */
public class NotExistsException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String message;

    public NotExistsException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    
}