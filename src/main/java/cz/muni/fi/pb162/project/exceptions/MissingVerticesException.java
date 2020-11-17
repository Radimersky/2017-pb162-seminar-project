package cz.muni.fi.pb162.project.exceptions;

/**
 * Exception occurs when amount of vertices in collection is 2 or less.
 *
 * @author Marek Radiměřský
 */

public class MissingVerticesException extends Exception {
    /**
     * Exception constructor.
     * @param message of exception
     */
    public MissingVerticesException(String message) {
        super(message);
    }

    /**
     * Exception constructor.
     * @param message of exception
     * @param cause of exception
     */
    public MissingVerticesException(String message, Throwable cause) {
        super(message, cause);
    }
}
