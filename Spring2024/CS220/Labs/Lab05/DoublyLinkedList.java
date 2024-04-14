package Spring2024.CS220.Labs.Lab05;

import java.util.Iterator;

/**
 * A doubly linked list implementation. This list is not circular and does not have sentinel nodes.
 * Implements the Iterable interface.
 *
 * @param <E> The type of elements stored in the list
 * @see Iterable
 * @see ListNode
 * @author Marcus Clements
 * @since 2024-4-9
 */
public class DoublyLinkedList<E> implements Iterable<E> {

    private int size;
    private ListNode head, tail;

    /**
     * Constructs a new DoublyLinkedList object with size 0 and null head and tail nodes.
     */
    public DoublyLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    /**
     * A private inner class that represents a node in the doubly linked list.
     */
    private class ListNode {
        private E data;
        private ListNode nextNode, prevNode;

        /**
         * Constructs a new ListNode object with the given data and null next and previous nodes.
         */
        public ListNode(E e) {
            this.data = e;
            this.nextNode = null;
            this.prevNode = null;
        }

    }

    /**
     * Adds the given element to the end of the list.
     *
     * @param e The element to add to the list
     */
    public void add(E e) {

        ListNode newNode = new ListNode(e);

        if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
            return;
        }

        tail.nextNode = newNode;
        newNode.prevNode = tail;
        tail = newNode;
        size++;

    }

    /**
     * Adds the given element to the list at the specified index.
     *
     * @param e     The element to add to the list
     * @param index The index at which to add the element
     */
    public void add(E e, int index) throws IndexOutOfBoundsException {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        ListNode newNode = new ListNode(e);

        if (index == 0) {
            newNode.nextNode = head;
            head.prevNode = newNode;
            head = newNode;
            size++;
            return;
        }

        if (index == size) {
            tail.nextNode = newNode;
            newNode.prevNode = tail;
            tail = newNode;
            size++;
            return;
        }

        ListNode curNode = head;

        for (int i = 0; i < index - 1; i++) {
            curNode = curNode.nextNode;
        }
        //
        newNode.nextNode = curNode.nextNode;
        newNode.prevNode = curNode;
        curNode.nextNode.prevNode = newNode;
        curNode.nextNode = newNode;
        size++;

    }

    /**
     * Removes the last element from the list.
     *
     * @return The element that was removed from the list
     */
    public E removeLast() {

        if (tail == null)
            return null;

        if (this.head == tail) {
            E toReturn = tail.data;
            head = null;
            tail = null;
            return toReturn;
        }
        // The list has more than one element
        E toReturn = tail.data;
        ListNode tempNode = tail;
        tail = tail.prevNode;
        tail.nextNode = null;
        tempNode.prevNode = null;
        size--;
        return toReturn;

    }

    /**
     * Removes the first element from the list.
     *
     * @return The element that was removed from the list
     */
    public E removeFirst() {

        if (head == null)
            return null;

        if (this.head == tail) {
            E toReturn = head.data;
            head = null;
            tail = null;
            return toReturn;
        }
        // The list has more than one element
        E toReturn = head.data;
        ListNode tempNode = head;
        head = head.nextNode;
        head.prevNode = null;
        tempNode.nextNode = null;
        size--;
        return toReturn;

    }

    /**
     * Removes the element at the specified index from the list.
     *
     * @param index The index of the element to remove
     * @return The element that was removed from the list
     * @throws IndexOutOfBoundsException If the index is out of bounds
     */
    public E remove(int index) throws IndexOutOfBoundsException {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            return removeFirst();
        }

        if (index == size - 1) {
            return removeLast();
        }

        if ((index > (size / 2))) { // If the index is closer to the end of the list
            ListNode curNode = tail;
            for (int i = size - 1; i > index; i--) {
                curNode = curNode.prevNode;
            }
            // curNode is now at the desired index, given no sentinel nodes
            E toReturn = curNode.data;
            curNode.prevNode.nextNode = curNode.nextNode;
            curNode.nextNode.prevNode = curNode.prevNode;
            curNode.prevNode = null;
            curNode.nextNode = null;
            size--;
            return toReturn;
        } else {

            ListNode curNode = head;

            for (int i = 0; i < index; i++) {
                curNode = curNode.nextNode;
            }

            // curNode is now at the desired index, given no sentinel nodes
            E toReturn = curNode.data;
            curNode.prevNode.nextNode = curNode.nextNode;
            curNode.nextNode.prevNode = curNode.prevNode;
            curNode.prevNode = null;
            curNode.nextNode = null;
            size--;
            return toReturn;
        }
    }

    /**
     * Removes the first occurrence of the given element from the list.
     *
     * @param e The element to remove from the list
     * @return The element that was removed from the list
     */
    public E removeFirstOccurance(E e) {

        if (head == null) {
            return null; // List is empty, so return null
        }

        if (head.data.equals(e)) {
            // The head node is the node to be removed
            return removeFirst(); // Use the removeFirst() method to remove the head node
        }

        ListNode curNode = head.nextNode; // Start from the second node

        while (curNode != null) {
            if (curNode.data.equals(e)) {
                E toReturn = curNode.data;
                curNode.prevNode.nextNode = curNode.nextNode;
                if (curNode.nextNode != null) {
                    curNode.nextNode.prevNode = curNode.prevNode;
                }
                // curNode is now at the desired index, given no sentinel nodes
                curNode.prevNode = null;
                curNode.nextNode = null;
                size--;
                return toReturn;
            }
            curNode = curNode.nextNode;
        }

        return null; // Element not found in the list

    }

    /**
     * Gets the size of the list.
     *
     * @return The size of the list as an integer
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the list is empty.
     *
     * @return True if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list of all elements. This is done by setting the head and
     * tail nodes to null and the size to 0, thereby removing all references
     * to the nodes in the list.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Gets the element at the specified index in the list.
     *
     * @param index The index of the element to get
     * @return The element at the specified index
     * @throws IndexOutOfBoundsException If the index is out of bounds
     */
    public E get(int index) throws IndexOutOfBoundsException {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (head == null) {
            return null;
        }

        if ((index > (size / 2))) { // If the index is closer to the end of the list
            ListNode curNode = tail;
            for (int i = size - 1; i > index; i--) {
                curNode = curNode.prevNode;
            }
            return curNode.data;
        } else { // If the index is closer to the beginning of the list

            ListNode curNode = head;

            for (int i = 0; i < index; i++) {
                curNode = curNode.nextNode;
            }

            if (curNode == null) {
                return null;
            }

            return curNode.data;
        }
    }

    /**
     * Sets the element at the specified index in the list to the given element.
     *
     * @param index The index of the element to set
     * @param e     The element to set at the specified index
     * @return The element that was previously at the specified index
     * @throws IndexOutOfBoundsException If the index is out of bounds
     */
    public E set(int index, E e) throws IndexOutOfBoundsException {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if ((index > (size / 2))) { // If the index is closer to the end of the list
            ListNode curNode = tail;
            for (int i = size - 1; i > index; i--) {
                curNode = curNode.prevNode;
            }
            E toReturn = curNode.data;
            curNode.data = e;
            return toReturn;
        } else { // If the index is closer to the beginning of the list

            ListNode curNode = head;

            for (int i = 0; i < index; i++) {
                curNode = curNode.nextNode;
            }

            E toReturn = curNode.data;
            curNode.data = e;
            return toReturn;
        }
    }

    /**
     * Gets the index of the first occurrence of the given element in the list.
     *
     * @param e The element to search for in the list
     * @return The index of the first occurrence of the element in the list, or -1 if the element is not in the list
     */
    public int indexOf(E e) {

        ListNode curNode = head;
        int index = 0;

        while (curNode != null) {
            if (curNode.data.equals(e)) {
                return index;
            }
            curNode = curNode.nextNode;
            index++;
        }

        return -1;

    }

    /**
     * Checks if the list contains the given element.
     *
     * @param e The element to search for in the list
     * @return True if the list contains the element, false otherwise
     */
    public boolean contains(E e) {
        return indexOf(e) != -1;
    }

    /**
     * Gets the index of the last occurrence of the given element in the list.
     *
     * @param e The element to search for in the list
     * @return The index of the last occurrence of the element in the list, or -1 if the element is not in the list
     */
    public int lastIndexOf(E e) {

        ListNode curNode = tail;
        int index = size - 1;

        while (curNode != null) {
            if (curNode.data.equals(e)) {
                return index;
            }
            curNode = curNode.prevNode;
            index--;
        }

        return -1;

    }

    /**
     * Gets the first element in the list.
     *
     * @return The first element in the list
     */
    public E getFirst() {
        return head.data;
    }

    /**
     * Gets the last element in the list.
     *
     * @return The last element in the list
     */
    public E getLast() {
        return tail.data;
    }

    /**
     * Adds the given element to the beginning of the list.
     *
     * @param e The element to add to the list
     */
    public void addFirst(E e) {
        add(e, 0);
    }

    /**
     * Adds the given element to the end of the list.
     *
     * @param e The element to add to the list
     */
    public void addLast(E e) {
        add(e);
    }

    /**
     * Removes the first element from the list.
     *
     * @return The element that was removed from the list
     */
    public Iterator<E> iterator() {
        return new DLLReverseIterator();
    }

    /**
     * A private inner class that represents an iterator for the doubly linked list.
     */
    private class DLLReverseIterator implements Iterator<E> {

        private ListNode curNode;

        /**
         * Constructs a new DLLReverseIterator object with the current node set to the tail node.
         */
        public DLLReverseIterator() {
            curNode = tail;
        }

        /**
         * Checks if there is a next element in the list.
         *
         * @return True if there is a next element, false otherwise
         */
        public boolean hasNext() {
            return curNode.prevNode != null;
        }

        /**
         * Gets the next element in the list.
         *
         * @return The next element in the list
         */
        public E next() {
            E toReturn = curNode.data;
            curNode = curNode.prevNode;
            return toReturn;
        }

    }

    /**
     * Returns a string representation of the doubly linked list.
     *
     * @return A string representation of the doubly linked list
     */
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("DoublyLinkedList [size=").append(size).append(", head=").append((head == null) ? "null" : head.data).append(" ->");
        if (size > 1) {
            for (int i = 0; i < size - 1; i++) {
                sb.append(get(i)).append(" <-> ");
            }
            sb.append(get(size - 1));
        }

        sb.append(" <- tail= ").append((tail == null) ? "null" : tail.data).append("]");

        return sb.toString();

    }

}
