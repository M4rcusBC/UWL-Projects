/**
 * This class represents a SpeechNotUnderstoodException, which is a type of Exception.
 * It is thrown when a speech command is not recognized.
 *
 * @author Marcus Clements
 * @since 2024-02-21
 */
public class SpeechNotUnderstoodException extends Exception {

    /**
     * Constructor for the SpeechNotUnderstoodException class.
     *
     * @param message The message of the exception.
     */
    public SpeechNotUnderstoodException(String message) {
        super(message); // Call the superclass constructor with the message
    }
    
}
