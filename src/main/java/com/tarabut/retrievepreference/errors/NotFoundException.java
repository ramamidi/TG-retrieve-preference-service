package com.tarabut.retrievepreference.errors;

/**
 * Not Found Exception Handler .
 */
public class NotFoundException extends RuntimeException {

    /**
     * Not Found Exception .
     *
     * @param message .
     */
    public NotFoundException(String message) {
        super(message);
    }
}
