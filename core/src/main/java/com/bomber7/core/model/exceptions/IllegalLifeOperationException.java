package com.bomber7.core.model.exceptions;
/**
 * IllegalLifeSetException is raised if illegal value for life is tried to be set.
 */
public class IllegalLifeOperationException extends RuntimeException {

    /**
	 * IllegalLifeOperationException Exception constructor.
	 * @param message the message
	 */
    public IllegalLifeOperationException(String message) {
        super(message);
    }
}
