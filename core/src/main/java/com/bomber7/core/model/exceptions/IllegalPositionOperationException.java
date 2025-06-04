package com.bomber7.core.model.exceptions;
/**
 * IllegalPositionSetException is raised if illegal value for x or y (Axis-Position) is tried to be set.
 */
public class IllegalPositionOperationException extends RuntimeException {

    /**
	 * IllegalPositionSetException Exception constructor.
	 * @param message the message
	 */
    public IllegalPositionOperationException(String message) {
        super(message);
    }

}
