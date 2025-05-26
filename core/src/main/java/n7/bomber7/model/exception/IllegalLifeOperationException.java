package n7.bomber7.model.exception;

/**
 * IllegalLifeSetException is raised if illegal value for life is tried to be set.
 * @author Pierre Chaveroux
 * @version Sprint n°1
 */
public class IllegalLifeOperationException extends RuntimeException {
    
    /**
	 * IllegalLifeSetException Exception constructor
	 * @param message
	 */
    public IllegalLifeOperationException(String message) {
        super(message);
    }
}
