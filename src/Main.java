// ============================================================
// Main.java  -  Digital Vinyl Store
//
// Run it and pick a topic number. Each topic shows ONE part of the syllabus.
//   javac -d out src/*.java
//   java -cp out Main        (shows the menu)
//   java -cp out Main 4      (runs topic 4 directly)
// ============================================================

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // If a number is given on the command line, run that topic and stop.
        if (args.length > 0) {
            runTopic(Integer.parseInt(args[0]));
            return;
        }

        Scanner sc = new Scanner(System.in);
        StoreApp.loadCatalogue();
        while (true) {
            printWelcome();
            int choice = StoreApp.readNumber(sc, "  Choose an option: ");
            if (choice == 1) {
                StoreApp.browse(sc);
            } else if (choice == 2) {
                StoreApp.search(sc);
            } else if (choice == 3) {
                StoreApp.viewCart(sc);
            } else if (choice == 4) {
                topicMenu(sc);
            } else if (choice == 0) {
                System.out.println("  Thanks for visiting. Keep spinning!");
                break;
            } else {
                System.out.println("  Please choose 0 to 4.");
            }
        }
        sc.close();
    }

    static void printWelcome() {
        System.out.println();
        System.out.println("  ==================================================");
        System.out.println("              WELCOME TO GROOVE STREET VINYL");
        System.out.println("         your record shop, right in the terminal");
        System.out.println("  ==================================================");
        System.out.println("    1. Browse and play records");
        System.out.println("    2. Search for an album");
        System.out.println("    3. View my cart");
        System.out.println("    4. OOP topic demos (for the viva)");
        System.out.println("    0. Exit");
    }

    // The OOP demo menu: one topic per number
    static void topicMenu(Scanner sc) {
        while (true) {
            printMenu();
            int choice = StoreApp.readNumber(sc, "Choose a topic number (0 to go back): ");
            if (choice == 0) {
                return;
            }
            runTopic(choice);
        }
    }

    static void printMenu() {
        System.out.println();
        System.out.println("==================================================");
        System.out.println("          DIGITAL VINYL STORE  -  OOP DEMO");
        System.out.println("==================================================");
        System.out.println("  1. Classes, Objects and Encapsulation");
        System.out.println("  2. Constructors and Method Overloading");
        System.out.println("  3. Static Members and Nested Classes");
        System.out.println("  4. Inheritance");
        System.out.println("  5. Polymorphism");
        System.out.println("  6. Abstract Classes and Interfaces");
        System.out.println("  7. Arrays and Collections");
        System.out.println("  8. Exception Handling");
        System.out.println("  9. Run ALL topics");
        System.out.println("  0. Back to the shop");
    }

    static void runTopic(int n) {
        switch (n) {
            case 1: topic1(); break;
            case 2: topic2(); break;
            case 3: topic3(); break;
            case 4: topic4(); break;
            case 5: topic5(); break;
            case 6: topic6(); break;
            case 7: topic7(); break;
            case 8: topic8(); break;
            case 9:
                for (int i = 1; i <= 8; i++) {
                    runTopic(i);
                }
                break;
            default: System.out.println("Please choose a number from 0 to 9.");
        }
    }

    // Small helpers to print headings
    static void heading(int n, String title) {
        System.out.println();
        System.out.println("##################################################");
        System.out.println("  TOPIC " + n + ": " + title);
        System.out.println("##################################################");
    }

    static void sub(String title) {
        System.out.println();
        System.out.println("--- " + title + " ---");
    }

    // ============================================================
    // TOPIC 1: Classes, Objects and Encapsulation
    // ============================================================
    static void topic1() {
        heading(1, "Classes, Objects and Encapsulation");

        sub("Creating two objects from the VinylRecord class");
        VinylRecord a = new VinylRecord("Random Access Memories", "Daft Punk", 2499, 10);
        VinylRecord b = new VinylRecord("Kind of Blue", "Miles Davis", 1899, 6);
        a.showDetails();
        b.showDetails();

        sub("Encapsulation: using getters and setters");
        // a.price = 100;   <- ERROR if you uncomment: price is private
        System.out.println("  a.getPrice() = " + a.getPrice());
        a.setPrice(2299);
        System.out.println("  after a.setPrice(2299): " + a.getPrice());

        sub("The setter protects the data");
        try {
            a.setPrice(-500);
        } catch (InvalidPriceException e) {
            System.out.println("  Rejected: " + e.getMessage());
        }
        System.out.println("  Price is still: " + a.getPrice());

        sub("Two references pointing to the SAME object");
        VinylRecord c = a;          // no "new", so no new object. c and a share one object.
        c.setStock(99);
        System.out.println("  c.setStock(99), now a.getStock() = " + a.getStock());

        sub("Reference reassignment");
        c = b;                      // c now points to b's object instead
        System.out.println("  c = b, now c.getName() = " + c.getName());

        sub("Independent objects keep their own state");
        System.out.println("  a stock = " + a.getStock() + ", b stock = " + b.getStock());

        sub("Passing an object to a method");
        System.out.println("  b stock before addOneCopy(b): " + b.getStock());
        addOneCopy(b);
        System.out.println("  b stock after  addOneCopy(b): " + b.getStock());
    }

    // The method gets a COPY of the reference, but it points to the SAME object.
    // So the change is seen outside too.
    static void addOneCopy(VinylRecord record) {
        record.setStock(record.getStock() + 1);
    }

    // ============================================================
    // TOPIC 2: Constructors and Method Overloading
    // ============================================================
    static void topic2() {
        heading(2, "Constructors and Method Overloading");

        sub("Default constructor (Java writes it for us)");
        Track t = new Track();   // Track.java has NO constructor
        System.out.println("  t.title = " + t.title + ", t.seconds = " + t.seconds);

        sub("No-argument constructor + chaining with this(...)");
        Product.showSteps = true;
        VinylRecord blank = new VinylRecord();
        Product.showSteps = false;
        blank.showDetails();

        sub("Parameterized constructor");
        VinylRecord ram = new VinylRecord("Random Access Memories", "Daft Punk", 2499, 10);
        ram.showDetails();

        sub("Copy constructor: a NEW object with the same values");
        VinylRecord copy = new VinylRecord(ram);
        copy.setPrice(1999);
        System.out.println("  original price = " + ram.getPrice() + ", copy price = " + copy.getPrice());
        System.out.println("  ram == copy ? " + (ram == copy) + "  (different objects)");

        sub("Method overloading: two restock() methods");
        ram.restock(5);
        ram.restock(8, "Vinyl World Distributors");

        sub("Java picks the method by the TYPE and NUMBER of arguments");
        show(5);
        show(5.5);
        show("Rumours");
        show(2, 3);

        sub("Type widening: a smaller type is automatically made bigger");
        char letter = 'A';
        show(letter);   // there is no show(char), so char -> int ('A' is 65)
        showLong(10);   // 10 is an int, it is widened to long
    }

    // Overloaded methods used in Topic 2
    static void show(int x) {
        System.out.println("  show(int)      got " + x);
    }

    static void show(double x) {
        System.out.println("  show(double)   got " + x);
    }

    static void show(String x) {
        System.out.println("  show(String)   got " + x);
    }

    static void show(int x, int y) {
        System.out.println("  show(int, int) got " + x + " and " + y);
    }

    static void showLong(long x) {
        System.out.println("  showLong(long) got " + x);
    }

    // ============================================================
    // TOPIC 3: Static Members and Nested Classes
    // ============================================================
    static void topic3() {
        heading(3, "Static Members and Nested Classes");

        sub("Static variable: one counter shared by all objects");
        System.out.println("  Product.productCount before = " + Product.productCount);
        VinylRecord a = new VinylRecord("Abbey Road", "The Beatles", 2299, 5);
        VinylRecord b = new VinylRecord("Thriller", "Michael Jackson", 1999, 7);
        System.out.println("  made 2 records, Product.productCount = " + Product.productCount);

        sub("Instance variables: each object has its own");
        a.setStock(1);
        System.out.println("  a stock = " + a.getStock() + ", b stock = " + b.getStock());

        sub("Static method vs instance method");
        Product.storeInfo();   // static: called on the CLASS
        a.showDetails();       // instance: called on an OBJECT

        sub("Order: static block -> instance block -> constructor");
        System.out.println("  (The static block already ran once, when Product was first used.)");
        Product.showSteps = true;
        new Cassette("Purple Rain", 899, 2);
        Product.showSteps = false;

        sub("Static nested class: Store.Receipt");
        Store.Receipt receipt = new Store.Receipt("Abbey Road", 2299);
        receipt.print();

        sub("Singleton: there is only ONE Store");
        Store s1 = Store.getInstance();
        Store s2 = Store.getInstance();
        System.out.println("  s1 == s2 ? " + (s1 == s2));

        sub("Inner class: Store.Cart (needs a Store object)");
        Store.Cart cart = s1.new Cart();
        cart.add(a);
        cart.add(b);
        System.out.println("  Cart total = Rs. " + cart.total());

        sub("Anonymous inner class: a class with no name, made on the spot");
        Playable radio = new Playable() {
            @Override
            public void play() {
                System.out.println("  Radio: playing whatever is on air");
            }
        };
        radio.play();
    }

    // ============================================================
    // TOPIC 4: Inheritance
    // ============================================================
    static void topic4() {
        heading(4, "Inheritance");

        sub("Single inheritance: VinylRecord extends Product");
        VinylRecord v = new VinylRecord("Rumours", "Fleetwood Mac", 2099, 8);
        System.out.println("  getName() comes from Product: " + v.getName());
        v.showDetails();   // VinylRecord's showDetails() calls super.showDetails()

        sub("Multilevel: LimitedEdition -> VinylRecord -> Product");
        System.out.println("  Constructors run from PARENT to CHILD:");
        Product.showSteps = true;
        LimitedEdition gold = new LimitedEdition("Kind of Blue", "Miles Davis", 4999, 3, 42);
        Product.showSteps = false;
        gold.showDetails();

        sub("Hierarchical: Cassette and VinylRecord both extend Product");
        Cassette tape = new Cassette("Nevermind", 799, 4);
        tape.showDetails();
        v.showDetails();

        sub("Multiple inheritance through interfaces");
        System.out.println("  VinylRecord implements Playable AND Discountable");
        v.play();
        System.out.println("  10% discount: Rs. " + v.applyDiscount(10));

        sub("super keyword: child calls the parent's version");
        gold.play();   // LimitedEdition.play() calls super.play() first

        sub("protected field and final method");
        v.restock(2);  // restock() changes "stock", which is protected in Product
        System.out.println("  getStoreName() is final, nobody can override it: " + gold.getStoreName());
    }

    // ============================================================
    // TOPIC 5: Polymorphism
    // ============================================================
    static void topic5() {
        heading(5, "Polymorphism");

        sub("Upcasting: a Product reference holds a VinylRecord object");
        Product p = new VinylRecord("Abbey Road", "The Beatles", 2299, 5);
        System.out.println("  p.getType() = " + p.getType() + "  (the OBJECT decides)");

        sub("Runtime polymorphism: same call, different behaviour");
        Product[] shelf = {
            new VinylRecord("Rumours", "Fleetwood Mac", 2099, 8),
            new Cassette("Nevermind", 799, 4),
            new LimitedEdition("Kind of Blue", "Miles Davis", 4999, 3, 42)
        };
        for (Product item : shelf) {
            item.showDetails();
        }

        sub("Variables vs methods: reference type vs object type");
        System.out.println("  p.category                = " + p.category + "   (variable: REFERENCE type decides)");
        System.out.println("  ((VinylRecord) p).category = " + ((VinylRecord) p).category);
        System.out.println("  p.getType()               = " + p.getType() + "     (method: OBJECT type decides)");

        sub("Downcasting: turning the Product reference back into VinylRecord");
        // p.cleanRecord();   <- ERROR: Product does not have cleanRecord()
        if (p instanceof VinylRecord) {
            VinylRecord v = (VinylRecord) p;
            v.cleanRecord();
        }
        try {
            Cassette wrong = (Cassette) p;   // p is really a VinylRecord, not a Cassette
            wrong.play();
        } catch (ClassCastException e) {
            System.out.println("  ClassCastException: a VinylRecord can not become a Cassette");
        }

        sub("Static method hiding vs instance method overriding");
        Product.storeInfo();
        VinylRecord.storeInfo();
        System.out.println("  Calling through Product p:");
        p.storeInfo();   // static  -> uses the REFERENCE type (Product)
        System.out.println("  p.getType() = " + p.getType() + "  (instance -> uses the OBJECT type)");

        sub("Overloading + overriding together");
        VinylRecord v = (VinylRecord) p;
        v.restock(1);                   // overloaded version 1 (compile time)
        v.restock(2, "EMI Records");    // overloaded version 2 (compile time)
        Playable one = v;
        Playable two = (Playable) shelf[2];   // shelf is Product[], so we cast
        one.play();                     // overridden in VinylRecord (run time)
        two.play();                     // overridden in LimitedEdition (run time)
    }

    // ============================================================
    // TOPIC 6: Abstract Classes and Interfaces
    // ============================================================
    static void topic6() {
        heading(6, "Abstract Classes and Interfaces");

        sub("Abstract class: you can NOT write new Product(...)");
        // Product x = new Product("X", 100, 1);   <- ERROR: Product is abstract
        Product[] items = {
            new VinylRecord("Rumours", "Fleetwood Mac", 2099, 8),
            new Cassette("Nevermind", 799, 4),
            new LimitedEdition("Kind of Blue", "Miles Davis", 4999, 3, 42)
        };
        System.out.println("  getType() is abstract in Product. Each child writes its own:");
        for (Product item : items) {
            System.out.println("    " + item.getClass().getSimpleName() + " -> " + item.getType());
        }

        sub("Concrete method in the abstract class (common workflow)");
        System.out.println("  showDetails() is written once in Product and calls getType():");
        items[1].showDetails();

        sub("Interface reference: Playable");
        Playable[] players = { (Playable) items[0], (Playable) items[1] };
        for (Playable pl : players) {
            pl.play();
            pl.stop();   // default method
        }

        sub("Interface inheritance: Collectible extends Discountable");
        Collectible c = (Collectible) items[2];
        System.out.println("  " + c.getCertificate());
        System.out.println("  Asked for 20% off, collectibles allow at most 5%: Rs. " + c.applyDiscount(20));

        sub("Abstract class + interfaces together");
        Product gold = items[2];
        System.out.println("  gold instanceof Product      ? " + (gold instanceof Product));
        System.out.println("  gold instanceof Playable     ? " + (gold instanceof Playable));
        System.out.println("  gold instanceof Discountable ? " + (gold instanceof Discountable));
        System.out.println("  gold instanceof Collectible  ? " + (gold instanceof Collectible));
    }

    // ============================================================
    // TOPIC 7: Arrays and Collections
    // ============================================================
    static void topic7() {
        heading(7, "Arrays and Collections");

        sub("Array of objects: Track[]");
        String[] titles = {"Get Lucky", "Contact", "Lose Yourself to Dance"};
        int[] seconds = {369, 381, 353};
        Track[] tracks = new Track[3];   // 3 empty slots (null)
        for (int i = 0; i < tracks.length; i++) {
            tracks[i] = new Track();
            tracks[i].title = titles[i];
            tracks[i].seconds = seconds[i];
        }
        for (Track t : tracks) {
            System.out.println("  " + t.title + " (" + t.getLength() + ")");
        }

        sub("Array reference: two names, one array");
        Track[] sameArray = tracks;
        sameArray[0].title = "Get Lucky (Radio Edit)";
        System.out.println("  changed through sameArray, tracks[0].title = " + tracks[0].title);

        sub("ArrayList: add, insert, update, remove");
        ArrayList<String> albums = new ArrayList<>();   // Generics: only Strings allowed
        albums.add("Rumours");
        albums.add("Thriller");
        albums.add("Nevermind");
        System.out.println("  start:             " + albums);
        albums.add(1, "Abbey Road");      // insert at index 1
        System.out.println("  add(1, ...):       " + albums);
        albums.set(0, "Rumours (Deluxe)"); // replace index 0
        System.out.println("  set(0, ...):       " + albums);
        albums.remove("Thriller");
        System.out.println("  remove(\"Thriller\"): " + albums);
        // albums.add(25);   <- ERROR: generics only allow String

        sub("Searching");
        System.out.println("  contains(\"Nevermind\") = " + albums.contains("Nevermind"));
        System.out.println("  indexOf(\"Abbey Road\")  = " + albums.indexOf("Abbey Road"));
        System.out.println("  get(2)                 = " + albums.get(2));
        System.out.println("  size()                 = " + albums.size());

        sub("Combining lists and taking a sublist");
        ArrayList<String> more = new ArrayList<>();
        more.add("Kind of Blue");
        more.add("Purple Rain");
        albums.addAll(more);
        System.out.println("  addAll(more):  " + albums);
        List<String> firstTwo = albums.subList(0, 2);   // index 0 and 1 (2 is NOT included)
        System.out.println("  subList(0, 2): " + firstTwo);

        sub("Iterator: go through and remove safely");
        Iterator<String> it = albums.iterator();
        while (it.hasNext()) {
            String name = it.next();
            if (name.startsWith("P")) {
                it.remove();   // removes "Purple Rain"
            }
        }
        System.out.println("  after removing names starting with P: " + albums);

        sub("ListIterator: forward, backward, replace, insert");
        ListIterator<String> li = albums.listIterator();
        System.out.print("  forward:  ");
        while (li.hasNext()) {
            System.out.print(li.next() + " | ");
        }
        System.out.println();
        System.out.print("  backward: ");
        while (li.hasPrevious()) {
            System.out.print(li.previous() + " | ");
        }
        System.out.println();
        li = albums.listIterator();
        while (li.hasNext()) {
            String name = li.next();
            if (name.equals("Nevermind")) {
                li.set("Nevermind (Remastered)");   // replace while walking
                li.add("In Utero");                 // insert right after it
            }
        }
        System.out.println("  after set + add: " + albums);

        sub("LinkedList as a QUEUE of customer orders");
        LinkedList<String> orders = new LinkedList<>();
        orders.offer("Order 1: Rumours");      // join the back of the queue
        orders.offer("Order 2: Thriller");
        orders.offer("Order 3: Abbey Road");
        System.out.println("  queue:  " + orders);
        System.out.println("  peek(): " + orders.peek() + "   (look, don't remove)");
        System.out.println("  poll(): " + orders.poll() + "   (take from the front)");
        System.out.println("  queue:  " + orders);

        sub("LinkedList: both ends");
        orders.addFirst("URGENT: Kind of Blue");
        orders.addLast("Order 4: Nevermind");
        System.out.println("  addFirst + addLast: " + orders);
        orders.removeFirst();
        orders.removeLast();
        System.out.println("  removeFirst + removeLast: " + orders);

        sub("Collection holding object references: the Store's products");
        StoreApp.loadCatalogue();   // puts the shop's 6 products into the Store
        Store store = Store.getInstance();
        for (Product p : store.getProducts()) {
            p.showDetails();
        }
        Product found = store.findByName("nevermind");
        System.out.println("  findByName(\"nevermind\") -> " + (found == null ? "not found" : found.getName()));
    }

    // ============================================================
    // TOPIC 8: Exception Handling
    // ============================================================
    static void topic8() {
        heading(8, "Exception Handling");

        VinylRecord ram = new VinylRecord("Random Access Memories", "Daft Punk", 2499, 3);

        sub("Normal flow: try -> finally (catch is skipped)");
        try {
            ram.sell(2);
            System.out.println("  try finished normally");
        } catch (OutOfStockException e) {
            System.out.println("  catch: " + e.getMessage());
        } finally {
            System.out.println("  finally: always runs");
        }

        sub("Exceptional flow: try -> catch -> finally");
        try {
            ram.sell(10);   // only 1 left, so this throws our CHECKED exception
            System.out.println("  this line is skipped");
        } catch (OutOfStockException e) {
            System.out.println("  catch: " + e.getMessage());
        } finally {
            System.out.println("  finally: always runs");
        }

        sub("Unchecked exception (our own): InvalidPriceException");
        try {
            ram.setPrice(-1);
        } catch (InvalidPriceException e) {
            System.out.println("  caught: " + e.getMessage());
        }

        sub("Multiple catch blocks");
        String[] prices = {"2499", "abc"};
        for (int i = 0; i <= 2; i++) {
            try {
                int price = Integer.parseInt(prices[i]);
                System.out.println("  index " + i + ": price = " + price);
            } catch (NumberFormatException e) {
                System.out.println("  index " + i + ": NumberFormatException, \"" + prices[i] + "\" is not a number");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("  index " + i + ": ArrayIndexOutOfBoundsException, the array has no index " + i);
            }
        }

        sub("Nested try + an exception thrown inside catch");
        try {
            try {
                int perCopy = 2499 / 0;   // ArithmeticException
                System.out.println("  " + perCopy);
            } catch (ArithmeticException e) {
                System.out.println("  inner catch: " + e.getMessage());
                throw new IllegalStateException("new exception thrown from inside catch");
            } finally {
                System.out.println("  inner finally: still runs");
            }
        } catch (IllegalStateException e) {
            System.out.println("  outer catch: " + e.getMessage());
        }

        sub("Exception propagation: methodC -> methodB -> methodA");
        methodA();

        sub("return and finally");
        int result = returnTest();
        System.out.println("  returnTest() gave back " + result);
    }

    static void methodA() {
        try {
            methodB();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("  methodA caught it: " + e.getMessage());
        }
    }

    static void methodB() {
        System.out.println("  methodB calls methodC (methodB has no try/catch)");
        methodC();
    }

    static void methodC() {
        System.out.println("  methodC reads index 5 of a 2-item array");
        int[] stock = new int[2];
        stock[5] = 1;   // throws, and the exception travels up to methodA
    }

    static int returnTest() {
        try {
            System.out.println("  try: about to return 1");
            return 1;
        } finally {
            System.out.println("  finally: runs even after return");
        }
    }
}
