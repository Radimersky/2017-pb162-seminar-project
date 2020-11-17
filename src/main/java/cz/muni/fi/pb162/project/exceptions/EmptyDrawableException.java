package cz.muni.fi.pb162.project.exceptions;

/**
 * Exception occurs when drawing nothing.
 *
 * @author Marek Radiměřský
 */
public class EmptyDrawableException extends Exception {
    /**
     * Exception constructor.
     * @param message of exception
     */
    public EmptyDrawableException(String message) {
        super(message);
    }

    /**
     * Exception constructor.
     * @param message of exception
     * @param cause of exception
     */
    public EmptyDrawableException(String message, Throwable cause) {
        super(message, cause);
    }
}
