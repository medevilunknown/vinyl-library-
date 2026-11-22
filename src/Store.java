// ============================================================
// Store.java  -  the shop itself
//
// Topics shown here:
//   3. SINGLETON class (only one Store object can ever exist)
//   3. STATIC NESTED class  (Receipt)
//   3. INNER (non-static) class  (Cart)
//   7. ArrayList of objects, searching
// ============================================================

import java.util.ArrayList;

public class Store {

    // ---------- SINGLETON ----------
    private static Store instance;   // the one and only Store object

    private String storeName = "Groove Street Vinyl";
    private ArrayList<Product> products = new ArrayList<>();   // Generics: only Products allowed

    // PRIVATE constructor: nobody outside can write  new Store()
    private Store() {
        System.out.println("  [Store] private constructor ran (this happens only once)");
    }

    // The only way to get the Store
    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    // Search: go through the list one by one
    public Product findByName(String name) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;   // not found
    }

    // ---------- STATIC NESTED CLASS ----------
    // Does NOT need a Store object.  Create it with:  new Store.Receipt(...)
    public static class Receipt {
        private String item;
        private double amount;

        public Receipt(String item, double amount) {
            this.item = item;
            this.amount = amount;
        }

        public void print() {
            System.out.println("  RECEIPT  " + item + " ........ Rs. " + amount);
        }
    }

    // ---------- INNER (non-static) CLASS ----------
    // NEEDS a Store object.  Create it with:  store.new Cart()
    // It can use the outer class's fields, like storeName.
    public class Cart {
        private ArrayList<Product> items = new ArrayList<>();

        public void add(Product p) {
            items.add(p);
            System.out.println("  Added to cart at " + storeName + ": " + p.getName());
        }

        public ArrayList<Product> getItems() {
            return items;
        }

        public void clear() {
            items.clear();
        }

        public double total() {
            double sum = 0;
            for (Product p : items) {
                sum = sum + p.getPrice();
            }
            return sum;
        }
    }
}
