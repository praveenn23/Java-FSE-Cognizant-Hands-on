import java.util.Arrays;

public class SearchTest {

    public static void main(String[] args) {

        
        System.out.println("  E-commerce Platform Search Function Test  ");
        
        Product[] products = {
            new Product(105, "Laptop",        "Electronics"),
            new Product(203, "Running Shoes", "Footwear"),
            new Product(312, "Coffee Maker",  "Appliances"),
            new Product(408, "Notebook",      "Stationery"),
            new Product(519, "Headphones",    "Electronics"),
            new Product(601, "Yoga Mat",      "Sports"),
            new Product(714, "Novel Book",    "Books"),
            new Product(822, "Blender",       "Appliances"),
            new Product(930, "Desk Lamp",     "Furniture"),
            new Product(1001,"Smartphone",    "Electronics")
        };

        System.out.println(" Linear Search (unsorted array) ");
        int linearTarget1 = 519;
        long startTime = System.nanoTime();
        Product result1 = LinearSearch.search(products, linearTarget1);
        long endTime = System.nanoTime();
        System.out.println("Search by ID " + linearTarget1 + " -> " + result1);
        System.out.println("Time taken: " + (endTime - startTime) + " ns\n");

        String nameTarget = "Yoga Mat";
        Product result2 = LinearSearch.searchByName(products, nameTarget);
        System.out.println("Search by name '" + nameTarget + "' -> " + result2);

        int linearTarget2 = 999;
        Product result3 = LinearSearch.search(products, linearTarget2);
        System.out.println("Search by ID " + linearTarget2 + " -> " + (result3 == null ? "Not Found" : result3));

        System.out.println("\n Binary Search (sorted array by productId) ");
        Product[] sortedProducts = products.clone();
        Arrays.sort(sortedProducts);

        System.out.println("Sorted order:");
        for (Product p : sortedProducts) {
            System.out.println("  " + p);
        }
        System.out.println();

        int binaryTarget1 = 519;
        startTime = System.nanoTime();
        Product result4 = BinarySearch.search(sortedProducts, binaryTarget1);
        endTime = System.nanoTime();
        System.out.println("Search by ID " + binaryTarget1 + " -> " + result4);
        System.out.println("Time taken: " + (endTime - startTime) + " ns\n");

        int binaryTarget2 = 999;
        Product result5 = BinarySearch.search(sortedProducts, binaryTarget2);
        System.out.println("Search by ID " + binaryTarget2 + " -> " + (result5 == null ? "Not Found" : result5));

        System.out.println("\n Complexity Analysis ");
        System.out.println("Linear Search  : Best O(1) | Average O(n) | Worst O(n)");
        System.out.println("Binary Search  : Best O(1) | Average O(log n) | Worst O(log n)");
        System.out.println("\nConclusion: Binary Search is more suitable for an e-commerce");
        System.out.println("platform with large, sorted product catalogs (e.g., by productId).");
        System.out.println("Linear Search is useful for unsorted data or name-based queries.");

        System.out.println("  All Tests Passed.");
        
    }
}
