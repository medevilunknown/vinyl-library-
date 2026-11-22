// ============================================================
// Product.java  -  the PARENT class of every item in our store
//
// Topics shown here:
//   1. Encapsulation (private fields + getters/setters)
//   3. Static variable, static block, instance block
//   4. protected, final method (used by child classes)
//   6. Abstract class and abstract method
//   8. throw and throws
// ============================================================

// "abstract" means we can NOT write  new Product(...)
// Product only exists so that VinylRecord and Cassette can share its code.
public abstract class Product {

    // ---------- STATIC variables: ONE copy, shared by ALL objects ----------
    static int productCount = 0;        // counts every product ever created
    static boolean showSteps = false;   // if true, blocks and constructors print what they do

    // ---------- STATIC BLOCK: runs only ONCE, when Java first loads this class ----------
    static {
        System.out.println("  [static block] Product class loaded (this runs only once)");
    }

    // ---------- INSTANCE variables: EACH object gets its own copy ----------
    private String name;          // private   -> only Product can touch it directly
    private double price;         // private   -> others must use getPrice() / setPrice()
    protected int stock;          // protected -> child classes can use it directly
    String category = "Product";  // no keyword = package-private (used in Topic 5)

    // ---------- INSTANCE BLOCK: runs EVERY time an object is created, before the constructor body ----------
    {
        productCount++;
        if (showSteps) System.out.println("  [instance block] Product part is being set up");
    }

    // ---------- CONSTRUCTOR ----------
    // An abstract class CAN have a constructor. Child classes call it using super(...).
    public Product(String name, double price, int stock) {
        this.name = name;   // "this.name" = the object's field, "name" = the parameter
        setPrice(price);    // use the setter so the price is checked
        setStock(stock);
        if (showSteps) System.out.println("  [constructor] Product(\"" + name + "\")");
    }

    // ---------- ABSTRACT METHOD: no body. Every child class MUST write its own ----------
    public abstract String getType();

    // ---------- CONCRETE METHOD: has a body, every child gets it for free ----------
    // It calls getType(), so each child prints its OWN type. (A common workflow.)
    public void showDetails() {
        System.out.println("  " + getType() + ": " + name + " | Rs. " + price + " | stock " + stock);
    }

    // ---------- FINAL METHOD: children can USE it but can NOT override it ----------
    public final String getStoreName() {
        return "Groove Street Vinyl";
    }

    // ---------- STATIC METHOD: belongs to the class (Topic 5: method hiding) ----------
    public static void storeInfo() {
        System.out.println("  Product.storeInfo()     -> we sell music products");
    }

    // ---------- "throws": this method may throw a CHECKED exception ----------
    // So whoever calls sell() MUST use try/catch (or also write "throws").
    public void sell(int quantity) throws OutOfStockException {
        if (quantity > stock) {
            throw new OutOfStockException("Only " + stock + " left of \"" + name + "\", but you asked for " + quantity);
        }
        stock = stock - quantity;
        System.out.println("  Sold " + quantity + " x \"" + name + "\". Stock left: " + stock);
    }

    // ---------- GETTERS and SETTERS (Encapsulation) ----------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new InvalidPriceException("Price cannot be negative: " + price);
        }
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative: " + stock);
        }
        this.stock = stock;
    }
}
