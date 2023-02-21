public class InsufficientCoinsException extends Exception{
    public InsufficientCoinsException() {}

    public InsufficientCoinsException(String message) {
        super(message);
    }
}
