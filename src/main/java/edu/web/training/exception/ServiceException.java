package edu.web.training.exception;

public class ServiceException extends Exception {
    // Default constructor
    public ServiceException() {
        super();
    }

    // Constructor that accepts a message
    public ServiceException(String message) {
        super(message);
    }

    // Constructor that accepts a message and cause
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor that accepts a cause
    public ServiceException(Throwable cause) {
        super(cause);
    }

}
