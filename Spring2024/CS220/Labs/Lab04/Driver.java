import java.util.Iterator;

public class Driver {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        // Testing add(e)
        list.add(10);
        System.out.println("After adding 10: " + list);  // Output: LinkedList [size=1, data=[10]]

        // Testing add(index, e)
        list.add(0, 5);
        System.out.println("After adding 5 at index 0: " + list);  // Output: LinkedList [size=2, data=[5, 10]]

        // Testing remove()
        System.out.println("Removed first element: " + list.remove());  // Output: 5
        System.out.println("List after removal: " + list);  // Output: LinkedList [size=1, data=[10]]

        // Testing remove(index)
        System.out.println("Removed element at index 0: " + list.remove((int)0));  // Output: 10
        System.out.println("List after removal: " + list);  // Output: LinkedList [size=0, data=[]]

        // Testing remove(e)
        list.add(4);
        list.add(4);
        System.out.println("List after adding 4 twice: " + list);  // Output: LinkedList [size=2, data=[4, 4]]
        System.out.println("Removed first occurrence of 4: " + list.remove((Integer) 4));  // Output: true
        System.out.println("List after removal: " + list);  // Output: LinkedList [size=1, data=[4]]

        // Testing isEmpty()
        System.out.println("List is empty: " + list.isEmpty());  // Output: false
        list.clear();
        System.out.println("List is empty: " + list.isEmpty());  // Output: true

        // Testing size()
        System.out.println("Initial size: " + list.size());  // Output: 0
        list.add(2);
        list.add(3);
        System.out.println("List after adding elements: " + list);  // Output: LinkedList [size=2, data=[2, 3]]

        // Testing get(index)
        System.out.println("Element at index 1: " + list.get(1));  // Output: 2

        // Testing set(index, e)
        System.out.println("Setting element at index 0 to 1: " + list.set(0, 1));  // Output: 2

        // Testing indexOf(e)
        System.out.println("(first) Index of 3: " + list.indexOf(3));  // Output: 1

        // Testing contains(e)
        System.out.println("List contains 1: " + list.contains(1));  // Output: true

        // Testing lastIndexOf(e)
        list.add(3);
        System.out.println("List: " + list);  // Output: LinkedList [size=3, data=[1, 3, 3]]
        System.out.println("Last index of 3: " + list.lastIndexOf(3));  // Output: 2

        // Testing getFirst()
        System.out.println("First element: " + list.getFirst());  // Output: 1

        // Testing getLast()
        System.out.println("Last element: " + list.getLast());  // Output: 3

        // Testing addFirst(e)
        list.addFirst(0);
        System.out.println("List after adding 0 to the beginning: " + list);  // Output: LinkedList [size=4, data=[0, 1, 3, 3]]

        // Testing addLast(e)
        list.addLast(4);
        System.out.println("List after adding 4 to the end: " + list);  // Output: LinkedList [size=5, data=[0, 1, 3, 3, 4]]

        System.out.println("\n------------------------\n\nNow testing iterator\nPrinting all elements in list\n");

        Iterator<Integer> iter = list.iterator();

        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        System.out.println("Testing iter.remove()");
        iter.remove();

        System.out.println("Printing all elements in list after removing head element: ");
        iter = list.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }



    }
}