# Digital Vinyl Store (Java)

COMP205 Object-Oriented Programming final project.

A record shop you use in the terminal. Browse vinyl records, limited editions and cassettes,
pick one, see its tracks and play them, add records to a cart and buy them.
Each part of the OOP syllabus also has its own numbered topic demo, so you can run and explain one topic at a time.

## How to run

You need Java 8 or newer (`java -version` to check).

```sh
javac -d out src/*.java     # compile all files into the "out" folder
java -cp out Main           # open the shop (welcome screen)
java -cp out Main 4         # run OOP topic 4 directly
java -cp out Main 9         # run all 8 OOP topics
```

## The shop

```
  ==================================================
              WELCOME TO GROOVE STREET VINYL
         your record shop, right in the terminal
  ==================================================
    1. Browse and play records
    2. Search for an album
    3. View my cart
    4. OOP topic demos (for the viva)
    0. Exit
```

Pick a record by its number to see its tracks. Then you can:

- play one track or the whole album (a progress bar shows it playing, sped up)
- add it to your cart
- buy it now (asking for more copies than are in stock shows an error)

## OOP topic menu (option 4)

| No. | Topic | Main files |
|-----|-------|------------|
| 1 | Classes, Objects and Encapsulation | `Product.java`, `Main.java` |
| 2 | Constructors and Method Overloading | `VinylRecord.java`, `Track.java` |
| 3 | Static Members and Nested Classes | `Product.java`, `Store.java` |
| 4 | Inheritance | `VinylRecord.java`, `LimitedEdition.java`, `Cassette.java` |
| 5 | Polymorphism | `Main.java` (topic5) |
| 6 | Abstract Classes and Interfaces | `Product.java`, `Playable.java`, `Discountable.java`, `Collectible.java` |
| 7 | Arrays and Collections | `Main.java` (topic7), `Store.java` |
| 8 | Exception Handling | `OutOfStockException.java`, `InvalidPriceException.java`, `Main.java` (topic8) |

## Class structure

```
Product (abstract)
├── VinylRecord        implements Playable, Discountable
│   └── LimitedEdition implements Collectible
└── Cassette           implements Playable

Collectible extends Discountable      (interface inheritance)

Store (Singleton)
├── Store.Receipt  (static nested class)
└── Store.Cart     (inner class)

StoreApp   the terminal shop screens (uses Store, Product, Track)
Track      one song; every Product holds a Track[]

OutOfStockException   extends Exception          (checked)
InvalidPriceException extends RuntimeException   (unchecked)
```

The older JavaScript version is kept in `js-version/`.
