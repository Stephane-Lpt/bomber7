package com.bomber7.core.model.exceptions;


/**
 * IllegalBombOperationException is raised if illegal value for bomb operation is tried to be set.
 */
public class IllegalBombOperationException extends RuntimeException {

    /**
	 * IllegalBombOperationException Exception constructor.
	 * @param message the message
	 */
    public IllegalBombOperationException(String message) {
        super(message);
    }

}
