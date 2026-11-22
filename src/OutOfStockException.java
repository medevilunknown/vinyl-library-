// ============================================================
// OutOfStockException.java  -  our own CHECKED exception
//
// It extends Exception, so Java FORCES the caller to either
// catch it (try/catch) or pass it on (throws).
// ============================================================

public class OutOfStockException extends Exception {

    public OutOfStockException(String message) {
        super(message);   // give the message to the parent Exception class
    }
}
