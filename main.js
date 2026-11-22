// main.js - Console demonstration for the Digital Vinyl Store OOP assignment
import VinylRecord from "./VinylRecord.js";

const DIVIDER = "=".repeat(60);
const LINE = "-".repeat(60);

function printSection(title) {
  console.log(`\n${LINE}\n${title}\n${LINE}`);
}

console.log(DIVIDER);
console.log("                  DIGITAL VINYL STORE");
console.log("                OOP CONSOLE DEMONSTRATION");
console.log(DIVIDER);

// CONSTRUCTOR OVERLOADING / DEFAULTS: No arguments use fallback values.
printSection("1. DEFAULT CONSTRUCTOR");
const defaultRecord = new VinylRecord();
console.log(defaultRecord.getDetails());

// CONSTRUCTOR OVERLOADING / DEFAULTS: Arguments create a customized record.
printSection("2. PARAMETERIZED CONSTRUCTOR");
const featuredRecord = new VinylRecord("Random Access Memories", "Daft Punk", 2499, 10);
console.log(featuredRecord.getDetails());

// ENCAPSULATION: The private fields are accessed through public getters/setters.
printSection("3. ENCAPSULATION AND GETTERS / SETTERS");
console.log(`Album accessed via getter: ${featuredRecord.albumName}`);
featuredRecord.artist = "Daft Punk (Electronic Duo)";
console.log(`Artist updated via setter: ${featuredRecord.artist}`);

printSection("4. VALIDATION USING SETTERS");
try {
  featuredRecord.price = -500;
} catch (error) {
  console.log(`Negative price rejected: ${error.message}`);
}
try {
  featuredRecord.stockCount = -3;
} catch (error) {
  console.log(`Negative stock rejected: ${error.message}`);
}
console.log(`Price after failed update : Rs. ${featuredRecord.price.toFixed(2)}`);
console.log(`Stock after failed update : ${featuredRecord.stockCount}`);

// METHOD OVERLOADING (SIMULATED): One argument restocks without a supplier.
printSection("5. RESTOCK METHOD - ONE ARGUMENT");
console.log("Calling restock(5)...");
const newStock = featuredRecord.restock(5);
console.log(`Restock successful. Updated stock: ${newStock}`);

// METHOD OVERLOADING (SIMULATED): Two arguments include a supplier confirmation.
printSection("6. RESTOCK METHOD - TWO ARGUMENTS");
console.log('Calling restock(8, "Vinyl World Distributors")...');
const confirmation = featuredRecord.restock(8, "Vinyl World Distributors");
console.log(confirmation);
console.log(`Updated stock: ${featuredRecord.stockCount}`);

printSection("7. FINAL RECORD DETAILS");
console.log("DEFAULT RECORD");
console.log(defaultRecord.getDetails());
console.log(`\n${LINE}`);
console.log("FEATURED RECORD");
console.log(featuredRecord.getDetails());
console.log(`\n${DIVIDER}`);
console.log("            OOP DEMONSTRATION COMPLETED");
console.log(DIVIDER);
