import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException; // Common exception with LinkedLists

/**
 * @author Marcus Clements @M4rcusBC on GitHub
 * @since 03/26/2024
 * @version 1.0
 * @see Spring.CS220.Labs
 * @implements Iterable<E>
 */
public class LinkedList<E> implements Iterable<E> {
    private ListNode firstNode; // ListNode will be a private inner class - defined within the class
    private int size;

    /**
     * The constructor for the LinkedList class. Sets the firstNode to null and the
     * size to 0.
     */
    public LinkedList() {
        firstNode = null;
        size = 0;
    }

    /**
     * Adds element to the head of the list
     * 
     * @param e The element to add to the list
     * @return True (Probably should be void..)
     */
    public boolean add(E e) {
        ListNode newNode = new ListNode(e);

        if (firstNode == null) {
            firstNode = newNode;
        } else {
            ListNode curNode = firstNode;

            while (curNode.nextNode != null) {
                curNode = curNode.nextNode;
            }
            curNode.nextNode = newNode;
        }

        size++;
        return true;
    }

    /**
     * Adds element to given index of the list
     * 
     * @param index The index in which to add the element
     * @param e     The element to add at the given index
     * @throws IndexOutOfBoundsException if the index is not within the size of the
     *                                   LinkedList instance
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        ListNode newNode = new ListNode(e);

        if (index == 0) {
            newNode.nextNode = firstNode;
            firstNode = newNode;
        } else {
            ListNode curNode = firstNode;
            for (int i = 0; i < index - 1; i++) {
                curNode = curNode.nextNode;
            }
            newNode.nextNode = curNode.nextNode;
            curNode.nextNode = newNode;
        }
        size++;
    }

    /**
     * Removes element from the head of the list
     * 
     * @return E The element that was removed from index 0
     */
    public E remove() {
        if (size == 0) { // Meaning value of firstNode is null
            throw new NoSuchElementException(); // We'll throw an exception if this is true
        }
        return remove(0);
    }

    /**
     * Removes element from given index in list
     * 
     * @param index The index to remove
     * @return E The element that was formerly in the given index
     * @throws IndexOutOfBoundsException if the index is not within the size of the
     *                                   LinkedList instance
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        E toReturn;

        if (index == 0) {
            toReturn = firstNode.value;
            firstNode = firstNode.nextNode;
            size--;
        } else {
            ListNode curNode = firstNode;
            for (int i = 0; i < index - 1; i++) {
                curNode = curNode.nextNode;
            }

            toReturn = curNode.nextNode.value;
            curNode.nextNode = curNode.nextNode.nextNode;

            size--;
        }
        return toReturn;
    }

    /**
     * Removes an element from a given index in the list
     * 
     * @param E the element to remove
     * @return True if element was removed, false if not found
     */
    public boolean remove(E e) {
        if (size == 0) {
            return false;
        } else if (firstNode.value.equals(e)) {
            firstNode = firstNode.nextNode;
            size--;
            return true;
        } else {
            ListNode curNode = firstNode;
            while (curNode.nextNode != null && !curNode.nextNode.value.equals(e)) {
                curNode = curNode.nextNode;
            }
            if (curNode.nextNode == null) {
                return false;
            } else {
                curNode.nextNode = curNode.nextNode.nextNode;
                size--;
                return true;
            }
        }
    }

    /**
     * Converts all elements into a List and returns a copy of it
     *
     * @return List<E> Representing all elements in the LinkedList
     */
    public List<E> toList() {
        List<E> list = new ArrayList<>();
        ListNode current = firstNode;
        while (current != null) {
            list.add(current.value);
            current = current.nextNode;
        }
        return list;
    }

    /**
     * Fetches the size of the LinkedList instance
     *
     * @return int Representing the number of elements in the LinkedList
     */
    public int size() {
        return this.size;
    }

    /**
     * Reports whether the LinkedList instance is empty
     * 
     * @return True if list size is 0, false otherwise
     */
    public boolean isEmpty() {
        if (size == 0)
            return true;
        return false;
    }

    /**
     * Clears all data from LinkedList instance and sets size to 0
     */
    public void clear() {
        firstNode = null; // Since this is a singly-linked list implementation, this line completely
                          // severs the ListNode chain and makes all data unreferencable
        size = 0;
    }

    /**
     * Fetches the element at the specified index if it's valid, but does not alter
     * it
     * 
     * @param int the index of the element to return
     * @return E the element at
     * @throws IndexOutOfBoundsException if the index is not within the size of the
     *                                   LinkedList instance
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            return firstNode.value;
        } else {
            ListNode curNode = firstNode;
            for (int i = 0; i < index; i++) {
                curNode = curNode.nextNode;
            }
            return curNode.value;
        }
    }

    /**
     * Sets the element at the specified index to the given element, and returns the
     * element that was replaced
     * 
     * @param index The index of the element to replace
     * @param e     The element to replace the current element with
     * @throws IndexOutOfBoundsException if the index is not within the size of the
     *                                   LinkedList instance
     * @return E The element that was replaced
     */
    public E set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        E toReturn;

        if (index == 0) {
            toReturn = firstNode.value;
            firstNode.value = e;
            return toReturn;
        } else {
            ListNode curNode = firstNode;
            for (int i = 0; i < index; i++) {
                curNode = curNode.nextNode;
            }
            toReturn = curNode.value;
            curNode.value = e;
            return toReturn;
        }
    }

    /**
     * Fetches the index of the first occurrence of the given element in the
     * LinkedList instance
     * 
     * @param e The element to search for
     * @return int The index of the first occurance of element e
     */
    public int indexOf(E e) {
        if (firstNode.value.equals(e))
            return 0;

        ListNode curNode = firstNode;
        int index = 0;

        while (curNode != null) {
            if (curNode.value.equals(e))
                return index;
            curNode = curNode.nextNode;
            index++;
        }

        return -1;

    }

    /**
     * Reports whether the LinkedList instance contains the element e passed in as
     * an argument.
     * 
     * @param e The element to be searched for in the LinkedList instance
     * @return True if the LinkedList contains the element e, false otherwise
     */
    public boolean contains(E e) {
        if (firstNode.value.equals(e))
            return true;

        ListNode curNode = firstNode;

        while (curNode != null) {
            if (curNode.value.equals(e))
                return true;
            curNode = curNode.nextNode;
        }

        return false;
    }

    /**
     * Reports the last occurance of an element in the LinkedList instance in the
     * form of its index.
     * 
     * @param e The element to be searched for in the LinkedList instance
     * @return The index of the last occurance of the element e in the LinkedList
     *         instance, -1 if the element is not found or the size is 0
     */
    public int lastIndexOf(E e) {

        int index = 0;
        int toReturn = -1;
        ListNode curNode = firstNode;
        while (curNode != null) {
            if (curNode.value.equals(e)) {
                toReturn = index;
            }
            curNode = curNode.nextNode;
            index++;
        }
        return toReturn;
    }

    /**
     * Fetches the first value in the LinkedList instance, but does not modify it.
     * 
     * @return E The first value in the LinkedList instance
     */
    public E getFirst() {
        return firstNode.value;
    }

    /**
     * Fetches the last value in the LinkedList instance, but does not modify it.
     * 
     * @return E The last value in the LinkedList instance
     */
    public E getLast() {
        if (size == 0) {
            return firstNode.value;
        }

        ListNode curNode = firstNode;

        while (curNode.nextNode != null) {
            curNode = curNode.nextNode;
        }

        return curNode.value;

    }

    /**
     * Adds an element to the head of the LinkedList instance
     * 
     * @param e The element to add to the head of the LinkedList instance
     */
    public void addFirst(E e) {
        ListNode newNode = new ListNode(e);

        newNode.nextNode = firstNode;
        firstNode = newNode;
        size++;
    }

    /**
     * Adds an element to the tail of the LinkedList instance
     * 
     * @param e The element to add to the tail of the LinkedList instance
     */
    public void addLast(E e) {
        if (size == 0) {
            this.add(e);
        } else {
            ListNode curNode = firstNode;
            while (curNode.nextNode != null) {
                curNode = curNode.nextNode;
            }
            curNode.nextNode = new ListNode(e);
            size++;
        }
    }

    /**
     * LinkedList inner class to represent a node in the list. Each node has a value
     * and a reference to the next node in the list.
     */
    private class ListNode {
        private E value;
        private ListNode nextNode;

        /**
         * Constructor for the ListNode class. Sets the value of the node to the given element
         */
        public ListNode(E e) {
            value = e;
            nextNode = null;
        }
    }

    /**
     * Returns a string representation of the LinkedList instance
     * 
     * @return String The string representation of the LinkedList instance
     */
    @Override
    public String toString() {
        return "LinkedList [size=" + this.size + ", data=" + this.toList() + "]";
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence. Required by the Iterable interface.
     * 
     * @return Iterator<E> An iterator over the elements in this list in proper sequence
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator(this); // Create and return a new LinkedListIterator
    }

    /**
     * Inner class that implements the Iterator interface to iterate over the elements in the LinkedList
     * instance in proper sequence.
     * 
     * @implements Iterator<E>
     */
    private class LinkedListIterator implements Iterator<E> {
        private ListNode current;
        private boolean removeCalledAfterNext; // Flag to track removal after next()

        /**
         * Constructor for the LinkedListIterator class. Sets the current node to the first node in the list.
         */
        public LinkedListIterator(LinkedList<E> list) {
            this.current = list.firstNode;
            this.removeCalledAfterNext = false;
        }

        /**
         * Returns true if the iteration has more elements.
         * 
         * @return True if the iteration has more elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next element in the iteration.
         * 
         * @return E The next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E value = current.value;
            removeCalledAfterNext = true; // Set flag after next() is called
            current = current.nextNode;
            return value;
        }

        /**
         * Removes from the underlying collection the last element returned by this iterator. This method can be called
         * only once per call to next(). The behavior of an iterator is unspecified if the underlying collection is modified
         * while the iteration is in progress in any way other than by calling this method.
         * 
         * @throws IllegalStateException if the next method has not yet been called, or the remove method has already been called after the last call to the next method
         */
        @Override
        public void remove() {
            if (!removeCalledAfterNext) { // Check if next() was called before remove()
                throw new IllegalStateException(); // Can't remove before next()
            }
            // Handle removing the head node by shifting the head pointer
            firstNode = firstNode.nextNode;
            size--;
            removeCalledAfterNext = false; // Reset flag after removal
        }
    }
}