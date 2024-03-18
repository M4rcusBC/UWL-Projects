public class Driver {

    public static void main(String[] args) {
        // Test with generics
        System.out.println("Testing with Generics:");
        ArrayList<String> stringList = new ArrayList<>();

        // Test add()
        System.out.println("\nTesting add():");
        stringList.add("Apple");
        stringList.add("Banana");
        stringList.add("Cherry");
        printArrayList(stringList);

        // Test add(int index, String str)
        System.out.println("\nTesting add(index, str):");
        stringList.add(1, "Orange");
        printArrayList(stringList);

        // Test remove(int index)
        System.out.println("\nTesting remove(index):");
        String removedItem = stringList.remove(2);
        System.out.println("Removed item: " + removedItem);
        printArrayList(stringList);

        // Test remove(String str)
        System.out.println("\nTesting remove(str):");
        stringList.remove("Apple");
        printArrayList(stringList);

        // Test size() and isEmpty()
        System.out.println("\nTesting size() and isEmpty():");
        System.out.println("Size: " + stringList.size());
        System.out.println("isEmpty: " + stringList.isEmpty());

        // Test clear()
        System.out.println("\nTesting clear():");
        stringList.clear();
        printArrayList(stringList); // Now shows size and data length consistent

        // Test ensureCapacity()
        System.out.println("\nTesting ensureCapacity():");
        stringList.ensureCapacity(20);
        System.out.println("Capacity increased (not directly observable, but size remains unaffected)");
        printArrayList(stringList); // Shows size remains 0

        // Test trimToSize()
        System.out.println("\nTesting trimToSize():");
        stringList.add("Grape");
        stringList.add("Mango");
        stringList.trimToSize();
        System.out.println("Data array trimmed (not directly observable, but size and data length reflect the change)");
        printArrayList(stringList); // Data length should now match size

        // Test get() and set()
        System.out.println("\nTesting get() and set():");
        String item = stringList.get(0);
        System.out.println("Item at index 0: " + item);
        stringList.set(1, "Pineapple");
        printArrayList(stringList);

        // Test indexOf() and contains()
        System.out.println("\nTesting indexOf() and contains():");
        int index = stringList.indexOf("Grape");
        System.out.println("Index of 'Grape': " + index);
        boolean contains = stringList.contains("Mango");
        System.out.println("Contains 'Mango': " + contains);

        // Test with custom object (Integer)
        System.out.println("\nTesting with Custom Object (Integer):");
        ArrayList<Integer> intList = new ArrayList<>();
        // intList.add(10);
        // intList.add(20);
        // intList.add(30);
        printArrayList(intList); // Should work with different data types
    }

    private static void printArrayList(ArrayList<?> list) {
        System.out.println(list.toString());
    }
}
