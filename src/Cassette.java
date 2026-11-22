// ============================================================
// Cassette.java  -  a cassette tape
//
// HIERARCHICAL inheritance: Cassette AND VinylRecord both extend Product.
// ============================================================

public class Cassette extends Product implements Playable {

    public Cassette(String name, double price, int stock) {
        super(name, price, stock);
        if (showSteps) System.out.println("  [constructor] Cassette(\"" + name + "\")");
    }

    @Override
    public String getType() {
        return "Cassette";
    }

    @Override
    public void play() {
        System.out.println("  Now playing \"" + getName() + "\" on the cassette deck");
    }

    // Cassette does NOT write stop(), so it uses the default stop() from Playable.
}
