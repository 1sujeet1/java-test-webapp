package com.example.javatest.exceptions;

/**
 * ConflictException
 */
public class ConflictException extends RuntimeException{

    

     /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String message;

    public ConflictException(String message) {
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