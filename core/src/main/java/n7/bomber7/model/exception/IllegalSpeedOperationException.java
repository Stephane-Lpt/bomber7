package n7.bomber7.model.exception;

/**
 * IllegalSpeedSetException is raised if illegal value for speed is tried to be set.
 * @author Pierre Chaveroux
 * @version Sprint n°1
 */
public class IllegalSpeedOperationException extends RuntimeException {
    
    /**
	 * IllegalSpeedSetException Exception constructor
	 * @param message
	 */
    public IllegalSpeedOperationException(String message) {
        super(message);
    }

}
