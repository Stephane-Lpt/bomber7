/**
 * IllegalLifeSetException is raised if illegal value for life is tried to be set.
 */
package com.bomber7.core.model.exceptions;
public class IllegalLifeOperationException extends RuntimeException {

    /**
	 * IllegalLifeSetException Exception constructor
	 * @param message
	 */
    public IllegalLifeOperationException(String message) {
        super(message);
    }
}
