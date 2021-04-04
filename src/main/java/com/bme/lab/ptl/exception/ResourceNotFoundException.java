package com.bme.lab.ptl.exception;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 3/27/21
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
