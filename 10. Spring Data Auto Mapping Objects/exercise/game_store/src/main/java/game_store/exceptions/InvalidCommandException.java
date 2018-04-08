package game_store.exceptions;

public class InvalidCommandException extends RuntimeException {

    public InvalidCommandException(final String message) {
        super(message);
    }
}
