# Digital Vinyl Store (Java)

COMP205 Object-Oriented Programming final project.

A small record shop program. It sells vinyl records, limited editions and cassettes.
Each part of the OOP syllabus has its own numbered topic in the menu, so you can run and explain one topic at a time.

## How to run

You need Java 8 or newer (`java -version` to check).

```sh
javac -d out src/*.java     # compile all files into the "out" folder
java -cp out Main           # open the menu
java -cp out Main 4         # run topic 4 directly
java -cp out Main 9         # run all 8 topics
```

## Menu

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

OutOfStockException   extends Exception          (checked)
InvalidPriceException extends RuntimeException   (unchecked)
```

The older JavaScript version is kept in `js-version/`.
