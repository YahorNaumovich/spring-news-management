package edu.web.training.exception;

public class DaoException extends Exception {
    // Default constructor
    public DaoException() {
        super();
    }

    // Constructor that accepts a message
    public DaoException(String message) {
        super(message);
    }

    // Constructor that accepts a message and cause
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor that accepts a cause
    public DaoException(Throwable cause) {
        super(cause);
    }

}
