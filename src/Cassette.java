// ============================================================
// Cassette.java  -  a cassette tape
//
// HIERARCHICAL inheritance: Cassette AND VinylRecord both extend Product.
// ============================================================

public class Cassette extends Product implements Playable {

    private String artist;

    // Two constructors = constructor overloading
    public Cassette(String name, double price, int stock) {
        this(name, "Unknown Artist", price, stock);
    }

    public Cassette(String name, String artist, double price, int stock) {
        super(name, price, stock);
        this.artist = artist;
        if (showSteps) System.out.println("  [constructor] Cassette(\"" + name + "\")");
    }

    public String getArtist() {
        return artist;
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
