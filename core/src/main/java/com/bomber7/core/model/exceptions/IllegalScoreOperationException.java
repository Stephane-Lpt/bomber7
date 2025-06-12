package com.bomber7.core.model.exceptions;
/**
 * IllegalScoreOperationException is raised if illegal value for score is tried to be set.
 */
public class IllegalScoreOperationException extends RuntimeException {
    /**
	 * IllegalScoreOperationException Exception constructor.
	 * @param message the message
	 */
    public IllegalScoreOperationException(String message) {
        super(message);
    }
}

