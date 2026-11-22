// ============================================================
// Playable.java  -  an INTERFACE: a list of things a class promises to do
// ============================================================

public interface Playable {

    // Abstract method (no body). Every class that implements Playable MUST write it.
    void play();

    // DEFAULT method (has a body). Classes get it for free, and MAY override it.
    default void stop() {
        System.out.println("  Stopped. (default method from the Playable interface)");
    }
}
