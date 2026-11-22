// ============================================================
// StoreApp.java  -  the shop you use in the terminal
//
// Browse records, pick one, see its tracks and play them,
// add them to your cart and buy them.
//
// It uses the classes we built: Store (Singleton), Store.Cart (inner class),
// Store.Receipt (static nested class), Product, VinylRecord, Cassette,
// Track arrays, Playable (polymorphism) and OutOfStockException (checked).
// ============================================================

import java.util.ArrayList;
import java.util.Scanner;

public class StoreApp {

    private static boolean loaded = false;   // has the catalogue been added to the Store yet?
    private static Store.Cart cart;          // the customer's cart (inner class object)

    // ---------- Put the records into the Store (only once) ----------
    public static void loadCatalogue() {
        if (loaded) {
            return;
        }
        loaded = true;
        Store store = Store.getInstance();

        VinylRecord ram = new VinylRecord("Random Access Memories", "Daft Punk", 2499, 10);
        ram.setTracks(makeTracks(
            new String[] {"Give Life Back to Music", "Get Lucky", "Contact"},
            new int[] {274, 369, 381}));

        VinylRecord rumours = new VinylRecord("Rumours", "Fleetwood Mac", 2099, 8);
        rumours.setTracks(makeTracks(
            new String[] {"Dreams", "The Chain", "Go Your Own Way"},
            new int[] {257, 270, 218}));

        VinylRecord abbey = new VinylRecord("Abbey Road", "The Beatles", 2299, 5);
        abbey.setTracks(makeTracks(
            new String[] {"Come Together", "Something", "Here Comes the Sun"},
            new int[] {260, 183, 185}));

        LimitedEdition blue = new LimitedEdition("Kind of Blue", "Miles Davis", 4999, 3, 42);
        blue.setTracks(makeTracks(
            new String[] {"So What", "Freddie Freeloader", "Blue in Green"},
            new int[] {562, 586, 337}));

        Cassette thriller = new Cassette("Thriller", "Michael Jackson", 899, 12);
        thriller.setTracks(makeTracks(
            new String[] {"Thriller", "Beat It", "Billie Jean"},
            new int[] {357, 258, 294}));

        Cassette nevermind = new Cassette("Nevermind", "Nirvana", 799, 4);
        nevermind.setTracks(makeTracks(
            new String[] {"Smells Like Teen Spirit", "Come as You Are"},
            new int[] {301, 219}));

        store.addProduct(ram);
        store.addProduct(rumours);
        store.addProduct(abbey);
        store.addProduct(blue);
        store.addProduct(thriller);
        store.addProduct(nevermind);

        cart = store.new Cart();   // inner class: needs a Store object
    }

    // Builds an array of Track objects from two arrays (titles and lengths)
    private static Track[] makeTracks(String[] titles, int[] seconds) {
        Track[] tracks = new Track[titles.length];
        for (int i = 0; i < titles.length; i++) {
            tracks[i] = new Track();
            tracks[i].title = titles[i];
            tracks[i].seconds = seconds[i];
        }
        return tracks;
    }

    // ---------- Reads a number typed by the user ----------
    // Returns 0 if input has ended, and -1 if the text is not a number.
    public static int readNumber(Scanner sc, String prompt) {
        System.out.print(prompt);
        if (!sc.hasNextLine()) {
            System.out.println();
            return 0;
        }
        String line = sc.nextLine().trim();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // ---------- Show every record and let the user pick one ----------
    public static void browse(Scanner sc) {
        loadCatalogue();
        ArrayList<Product> products = Store.getInstance().getProducts();
        while (true) {
            System.out.println();
            System.out.println("  RECORDS IN THE SHOP");
            System.out.println("  ---------------------------------------------------------------------------");
            System.out.println("   #  Title                     Artist            Format                 Price");
            System.out.println("  ---------------------------------------------------------------------------");
            for (int i = 0; i < products.size(); i++) {
                printRow(i + 1, products.get(i));
            }
            System.out.println("  ---------------------------------------------------------------------------");
            int choice = readNumber(sc, "  Pick a record number (0 to go back): ");
            if (choice == 0) {
                return;
            }
            if (choice < 1 || choice > products.size()) {
                System.out.println("  Please type a number from the list.");
                continue;
            }
            showRecord(sc, products.get(choice - 1));
        }
    }

    // ---------- Search by name ----------
    public static void search(Scanner sc) {
        loadCatalogue();
        System.out.print("  Type the album name: ");
        if (!sc.hasNextLine()) {
            return;
        }
        String name = sc.nextLine().trim();
        Product found = Store.getInstance().findByName(name);
        if (found == null) {
            System.out.println("  Sorry, we don't have \"" + name + "\".");
        } else {
            showRecord(sc, found);
        }
    }

    // One row of the table. String.format pads text so the columns line up.
    private static void printRow(int number, Product p) {
        System.out.println(String.format("  %2d  %-25s %-17s %-21s Rs. %.0f",
                number, p.getName(), artistOf(p), p.getType(), p.getPrice()));
    }

    // DOWNCASTING with instanceof: only VinylRecord and Cassette have getArtist()
    private static String artistOf(Product p) {
        if (p instanceof VinylRecord) {
            return ((VinylRecord) p).getArtist();
        }
        if (p instanceof Cassette) {
            return ((Cassette) p).getArtist();
        }
        return "-";
    }

    // ---------- One record: tracks and actions ----------
    private static void showRecord(Scanner sc, Product p) {
        while (true) {
            Track[] tracks = p.getTracks();
            System.out.println();
            System.out.println("  ==========================================");
            System.out.println("  " + p.getName() + "  -  " + artistOf(p));
            System.out.println("  " + p.getType() + " | Rs. " + p.getPrice() + " | " + p.getStock() + " in stock");
            System.out.println("  ==========================================");
            for (int i = 0; i < tracks.length; i++) {
                System.out.println(String.format("    %d. %-26s %s", i + 1, tracks[i].title, tracks[i].getLength()));
            }
            System.out.println();
            System.out.println("  1. Play a track");
            System.out.println("  2. Play the whole album");
            System.out.println("  3. Add to cart");
            System.out.println("  4. Buy now");
            System.out.println("  0. Back to the list");
            int choice = readNumber(sc, "  Choose: ");

            if (choice == 0) {
                return;
            } else if (choice == 1) {
                int t = readNumber(sc, "  Track number: ");
                if (t >= 1 && t <= tracks.length) {
                    playTrack(p, tracks[t - 1]);
                } else {
                    System.out.println("  There is no track " + t + " on this album.");
                }
            } else if (choice == 2) {
                for (Track track : tracks) {
                    playTrack(p, track);
                }
                System.out.println("  End of the album.");
            } else if (choice == 3) {
                cart.add(p);
            } else if (choice == 4) {
                buy(sc, p);
            } else {
                System.out.println("  Please choose 0 to 4.");
            }
        }
    }

    // ---------- Play one track with a progress bar ----------
    private static void playTrack(Product p, Track track) {
        System.out.println();
        // POLYMORPHISM: a turntable for vinyl, a cassette deck for cassettes
        if (p instanceof Playable) {
            ((Playable) p).play();
        }
        System.out.println("  >> " + track.title + "  (" + track.getLength() + ", sped up for the demo)");

        int steps = 30;
        for (int step = 0; step <= steps; step++) {
            int played = track.seconds * step / steps;
            String bar = repeat('#', step) + repeat('-', steps - step);
            // "\r" moves back to the start of the line, so the bar redraws in place
            System.out.print("\r  [" + bar + "] " + Track.formatTime(played) + " / " + track.getLength());
            System.out.flush();
            try {
                Thread.sleep(100);   // sleep() throws a CHECKED exception, so we must catch it
            } catch (InterruptedException e) {
                return;
            }
        }
        System.out.println();
        System.out.println("  Finished: " + track.title);
    }

    private static String repeat(char c, int times) {
        String s = "";
        for (int i = 0; i < times; i++) {
            s = s + c;
        }
        return s;
    }

    // ---------- Buying uses our CHECKED exception ----------
    private static void buy(Scanner sc, Product p) {
        int qty = readNumber(sc, "  How many copies? ");
        if (qty <= 0) {
            System.out.println("  Please type a number above 0.");
            return;
        }
        try {
            p.sell(qty);
            new Store.Receipt(qty + " x " + p.getName(), qty * p.getPrice()).print();
            System.out.println("  Thank you for shopping at " + p.getStoreName() + "!");
        } catch (OutOfStockException e) {
            System.out.println("  Sorry: " + e.getMessage());
        }
    }

    // ---------- Cart ----------
    public static void viewCart(Scanner sc) {
        loadCatalogue();
        ArrayList<Product> items = cart.getItems();
        System.out.println();
        if (items.isEmpty()) {
            System.out.println("  Your cart is empty. Pick a record and choose \"Add to cart\".");
            return;
        }
        System.out.println("  YOUR CART");
        for (Product p : items) {
            System.out.println(String.format("    %-28s Rs. %.0f", p.getName(), p.getPrice()));
        }
        System.out.println(String.format("    %-28s Rs. %.0f", "TOTAL", cart.total()));
        System.out.println();
        System.out.println("  1. Buy everything");
        System.out.println("  2. Empty the cart");
        System.out.println("  0. Back");
        int choice = readNumber(sc, "  Choose: ");
        if (choice == 1) {
            for (Product p : items) {
                try {
                    p.sell(1);
                    new Store.Receipt(p.getName(), p.getPrice()).print();
                } catch (OutOfStockException e) {
                    System.out.println("  Could not buy: " + e.getMessage());
                } finally {
                    System.out.println("  ----");
                }
            }
            cart.clear();
            System.out.println("  Thank you for shopping at " + Store.getInstance().getProducts().get(0).getStoreName() + "!");
        } else if (choice == 2) {
            cart.clear();
            System.out.println("  Cart emptied.");
        }
    }
}
