package n7.bomber7.model.exception;

/**
 * IllegalPositionSetException is raised if illegal value for x or y (Axis-Position) is tried to be set.
 * @author Pierre Chaveroux
 * @version Sprint n°1
 */
public class IllegalPositionOperationException extends RuntimeException {
    
    /**
	 * IllegalPositionSetException Exception constructor
	 * @param message
	 */
    public IllegalPositionOperationException(String message) {
        super(message);
    }

}
