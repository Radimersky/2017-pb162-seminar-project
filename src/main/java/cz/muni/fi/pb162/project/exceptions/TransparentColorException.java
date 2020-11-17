package cz.muni.fi.pb162.project.exceptions;

/**
 * Exception occurs when drawing with white pen on white paper.
 *
 * @author Marek Radiměřský
 */

public class TransparentColorException extends Exception {
    /**
     * Exception constructor.
     * @param message of exception
     */
    public TransparentColorException(String message) {
        super(message);
    }

    /**
     * Exception constructor.
     * @param message of exception
     * @param cause of exception
     */
    public TransparentColorException(String message, Throwable cause) {
        super(message, cause);
    }
}
