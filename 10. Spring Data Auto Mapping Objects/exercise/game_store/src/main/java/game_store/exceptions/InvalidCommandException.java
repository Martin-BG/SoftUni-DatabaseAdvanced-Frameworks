package game_store.exceptions;

public class InvalidCommandException extends RuntimeException {

    private static final long serialVersionUID = 5876844115664006274L;

    public InvalidCommandException(final String message) {
        super(message);
    }
}
