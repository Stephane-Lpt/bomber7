/**
 * IllegalBombOperationException is raised if illegal value for bomb operation is tried to be set.
 */

package com.bomber7.core.model.exceptions;

public class IllegalBombOperationException extends RuntimeException {

    /**
	 * IllegalBombOperationException Exception constructor.
	 * @param message
	 */
    public IllegalBombOperationException(String message) {
        super(message);
    }

}
