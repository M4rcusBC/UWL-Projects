package Spring2024.CS220.Labs.Lab05;

/**
 * A driver class to test the DoublyLinkedList class.
 *
 * The following methods are tested:
 * 1. add(e)
 * 2. add(index, e)
 * 3. remove(index)
 * 4. remove(e)
 * 5. removeFirstOccurance(e)
 * 6. isEmpty()
 * 7. size()
 * 8. get(index)
 * 9. addFirst(e)
 * 10. addLast(e)
 * 11. getFirst()
 * 12. getLast()
 * 13. lastIndexOf(e)
 * 14. iterator()
 *
 * @author Marcus Clements
 * @see DoublyLinkedList
 * @since 2024-4-9
 */
public class Driver {

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        // Testing add(e)
        list.add(10);
        System.out.println("After adding 10: " + list);  // Output: DoublyLinkedList [size=1, head=10, tail=10]

        // Testing add(index, e)
        list.add((Integer) 5, 0);
        System.out.println("After adding 5 at index 0: " + list);  // Output: DoublyLinkedList [size=2, head=5, tail=10]

        // Testing remove(index)
        System.out.println("Removed first element: " + list.remove(0));  // Output: 5
        System.out.println("List after removal: " + list);  // Output: DoublyLinkedList [size=1, head=10, tail=10]

        // Testing remove(E)
        System.out.println("Removed element at index 0: " + list.remove(0));  // Output: 10
        System.out.println("List after removal: " + list);  // Output: DoublyLinkedList [size=0, head=null, tail=null]

        // Testing remove(e)
        list.add(4);
        list.add(4);
        System.out.println("List after adding 4 twice: " + list);  // Output: DoublyLinkedList [size=2, head=4, tail=4]
        System.out.println("Removed first occurrence of 4: " + list.removeFirstOccurance((Integer) 4));  // Output: true
        System.out.println("List after removal: " + list);  // Output: DoublyLinkedList [size=1, head=4, tail=4]

        // Testing isEmpty()
        System.out.println("List is empty: " + list.isEmpty());  // Output: false
        list.clear();
        System.out.println("List is empty: " + list.isEmpty());  // Output: true

        // Testing size()
        System.out.println("Initial size: " + list.size());  // Output: 0
        list.add(2);
        list.add(3);
        System.out.println("List after adding elements: " + list);  // Output: DoublyLinkedList [size=2, head=2, tail=3]

        // Testing get(index)
        System.out.println("Element at index 1: " + list.get(1));  // Output: 3

        // Testing addFirst(e)
        list.addFirst(10);
        System.out.println("After adding 10 at the beginning: " + list);  // Expected Output: DoublyLinkedList [size=1, head=10, tail=10]

        // Testing addLast(e)
        list.addLast(20);
        System.out.println("After adding 20 at the end: " + list);  // Expected Output: DoublyLinkedList [size=2, head=10, tail=20]

        // Testing getFirst()
        System.out.println("First element: " + list.getFirst());  // Expected Output: 10

        // Testing getLast()
        System.out.println("Last element: " + list.getLast());  // Expected Output: 20

        // Testing lastIndexOf(e)
        list.addLast(10);
        System.out.println("After adding 10 at the end: " + list);  // Expected Output: DoublyLinkedList [size=3, head=10, tail=10]
        System.out.println("Last index of 10: " + list.lastIndexOf(10));  // Expected Output: 2

        // Testing iterator()
        System.out.println("Iterating in reverse order:");

        for (Integer num : list) { // Implicitly calls iterator()
            System.out.println(num);  // Expected Output: 10 20 3 2
        }
        System.out.println(list.getFirst());

    }
}
