// VinylRecord.js - Digital Vinyl Store (OOP Assignment)

export default class VinylRecord {
  // ENCAPSULATION: Private fields cannot be accessed directly outside this class.
  #albumName;
  #artist;
  #price;
  #stockCount;

  // DEFAULT + PARAMETERIZED CONSTRUCTOR:
  // Default parameters allow both new VinylRecord() and new VinylRecord(...).
  constructor(
    albumName = "Untitled Album",
    artist = "Unknown Artist",
    price = 0,
    stockCount = 0
  ) {
    // Use setters so constructor inputs follow the same validation rules.
    this.albumName = albumName;
    this.artist = artist;
    this.price = price;
    this.stockCount = stockCount;
  }

  // GETTERS & SETTERS: Controlled access to encapsulated attributes.
  get albumName() {
    return this.#albumName;
  }

  set albumName(value) {
    this.#albumName = value;
  }

  get artist() {
    return this.#artist;
  }

  set artist(value) {
    this.#artist = value;
  }

  get price() {
    return this.#price;
  }

  set price(value) {
    // VALIDATION: Price must be a finite, non-negative number.
    if (typeof value !== "number" || !Number.isFinite(value) || value < 0) {
      throw new Error("Invalid price: price must be a number greater than or equal to 0.");
    }
    this.#price = value;
  }

  get stockCount() {
    return this.#stockCount;
  }

  set stockCount(value) {
    // VALIDATION: Stock cannot be negative or fractional.
    if (!Number.isInteger(value) || value < 0) {
      throw new Error("Invalid stock: stock count must be a non-negative integer.");
    }
    this.#stockCount = value;
  }

  // METHOD OVERLOADING (SIMULATED): JavaScript selects behavior based on argument count.
  // restock(quantity) -> adds stock and returns the new count.
  // restock(quantity, supplierName) -> adds stock and returns a delivery confirmation.
  restock(quantity, supplierName) {
    if (arguments.length !== 1 && arguments.length !== 2) {
      throw new Error("Restock requires 1 or 2 arguments.");
    }
    if (!Number.isInteger(quantity) || quantity <= 0) {
      throw new Error("Invalid restock quantity: enter a positive integer.");
    }
    if (arguments.length === 2 &&
        (typeof supplierName !== "string" || supplierName.trim() === "")) {
      throw new Error("Invalid supplier name: enter a non-empty string.");
    }

    this.stockCount = this.#stockCount + quantity;

    if (arguments.length === 2) {
      return `Delivery confirmed: ${quantity} copies of "${this.#albumName}" received from ${supplierName}.`;
    }
    return this.#stockCount;
  }

  // DISPLAY HELPER: Returns a formatted, console-friendly description.
  getDetails() {
    return [
      `Album       : ${this.#albumName}`,
      `Artist      : ${this.#artist}`,
      `Price       : Rs. ${this.#price.toFixed(2)}`,
      `Stock Count : ${this.#stockCount}`
    ].join("\n");
  }
}
