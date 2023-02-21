public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException() {}

    public IncorrectPasswordException(String message) {
        super(message);
    }
}
