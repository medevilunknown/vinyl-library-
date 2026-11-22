// ============================================================
// LimitedEdition.java  -  a special numbered vinyl
//
// MULTILEVEL inheritance:  LimitedEdition -> VinylRecord -> Product
// Also implements Collectible (which itself extends Discountable)
// ============================================================

public class LimitedEdition extends VinylRecord implements Collectible {

    private int serialNumber;

    public LimitedEdition(String name, String artist, double price, int stock, int serialNumber) {
        super(name, artist, price, stock);   // calls VinylRecord's constructor
        this.serialNumber = serialNumber;
        if (showSteps) System.out.println("  [constructor] LimitedEdition(serial #" + serialNumber + ")");
    }

    @Override
    public String getType() {
        return "Limited Edition Vinyl";
    }

    @Override
    public void play() {
        super.play();   // run VinylRecord's play() first
        System.out.println("  (gold-coloured vinyl, serial #" + serialNumber + ")");
    }

    // Collectors' items get at most 5% off
    @Override
    public double applyDiscount(double percent) {
        if (percent > 5) {
            percent = 5;
        }
        return super.applyDiscount(percent);
    }

    @Override
    public String getCertificate() {   // from the Collectible interface
        return "Certificate: \"" + getName() + "\" is copy #" + serialNumber + " of 500";
    }

    // We can NOT write getStoreName() here: it is final in Product.
}
