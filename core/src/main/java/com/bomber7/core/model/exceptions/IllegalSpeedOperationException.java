/**
 * IllegalSpeedSetException is raised if illegal value for speed is tried to be set.
 */
package com.bomber7.core.model.exceptions;
public class IllegalSpeedOperationException extends RuntimeException {

    /**
	 * IllegalSpeedOperationException Exception constructor.
	 * @param message
	 */
    public IllegalSpeedOperationException(String message) {
        super(message);
    }

}
