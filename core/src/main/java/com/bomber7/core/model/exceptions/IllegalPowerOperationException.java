package com.bomber7.core.model.exceptions;

/**
 * IllegalPowerOperationException is raised if an illegal value for power is tried to be set.
 */

public class IllegalPowerOperationException extends RuntimeException {

    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
	 * IllegalPowerOperationException Exception constructor.
	 * @param message the message
	 */
    public IllegalPowerOperationException(String message) {
        super(message);
    }
}
