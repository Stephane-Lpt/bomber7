package com.bomber7.core.model.exceptions;
/**
 * IllegalSpeedSetException is raised if illegal value for speed is tried to be set.
 */
public class IllegalSpeedOperationException extends RuntimeException {

    /**
	 * IllegalSpeedOperationException Exception constructor.
	 * @param message the message
	 */
    public IllegalSpeedOperationException(String message) {
        super(message);
    }

}
