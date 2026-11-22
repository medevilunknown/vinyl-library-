// ============================================================
// InvalidPriceException.java  -  our own UNCHECKED exception
//
// It extends RuntimeException, so Java does NOT force you to catch it.
// ============================================================

public class InvalidPriceException extends RuntimeException {

    public InvalidPriceException(String message) {
        super(message);
    }
}
