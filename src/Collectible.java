// ============================================================
// Collectible.java  -  INTERFACE INHERITANCE
//
// An interface can EXTEND another interface.
// A class that implements Collectible must write BOTH:
//   getCertificate()   (from Collectible)
//   applyDiscount()    (from Discountable)
// ============================================================

public interface Collectible extends Discountable {

    String getCertificate();
}
