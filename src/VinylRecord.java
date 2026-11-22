// ============================================================
// VinylRecord.java  -  a vinyl record album
//
// Topics shown here:
//   2. No-arg, parameterized and copy constructors, this(), method overloading
//   4. Single inheritance (extends Product), super
//   4. Multiple inheritance through interfaces (implements Playable, Discountable)
//   5. Overriding with @Override, static method hiding, variable hiding
// ============================================================

public class VinylRecord extends Product implements Playable, Discountable {

    private String artist;
    String category = "Vinyl";   // same name as Product's variable (Topic 5)

    // 1) NO-ARGUMENT constructor
    //    this(...) calls ANOTHER constructor of the SAME class (constructor chaining).
    //    this(...) must be the FIRST line.
    public VinylRecord() {
        this("Untitled Album", "Unknown Artist", 0, 0);
        if (showSteps) System.out.println("  [constructor] VinylRecord()  <- no-arg body runs LAST");
    }

    // 2) PARAMETERIZED constructor
    //    super(...) calls the PARENT (Product) constructor. It must be the FIRST line.
    public VinylRecord(String name, String artist, double price, int stock) {
        super(name, price, stock);
        this.artist = artist;
        if (showSteps) System.out.println("  [constructor] VinylRecord(\"" + name + "\", \"" + artist + "\", ...)");
    }

    // 3) COPY constructor: builds a NEW object with the same values as "other"
    public VinylRecord(VinylRecord other) {
        this(other.getName(), other.artist, other.getPrice(), other.stock);
    }

    // ---------- METHOD OVERLOADING: same name, different parameters ----------
    public void restock(int quantity) {
        stock = stock + quantity;   // "stock" is protected in Product, so we can use it here
        System.out.println("  restock(int)         -> added " + quantity + ", stock is now " + stock);
    }

    public void restock(int quantity, String supplier) {
        stock = stock + quantity;
        System.out.println("  restock(int, String) -> " + quantity + " copies from " + supplier + ", stock is now " + stock);
    }

    // ---------- OVERRIDING: same method as the parent/interface, new body ----------
    @Override
    public String getType() {
        return "Vinyl";
    }

    @Override
    public void showDetails() {
        super.showDetails();   // first run Product's version...
        System.out.println("      artist: " + artist);   // ...then add the artist
    }

    @Override
    public void play() {   // from the Playable interface
        System.out.println("  Now playing \"" + getName() + "\" by " + artist + " on the turntable");
    }

    @Override
    public double applyDiscount(double percent) {   // from the Discountable interface
        return getPrice() - getPrice() * percent / 100;
    }

    // STATIC method with the same name as Product's -> this is HIDING, not overriding
    public static void storeInfo() {
        System.out.println("  VinylRecord.storeInfo() -> we sell vinyl records");
    }

    // A method only vinyl records have (used for downcasting in Topic 5)
    public void cleanRecord() {
        System.out.println("  Cleaning the grooves of \"" + getName() + "\"");
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
