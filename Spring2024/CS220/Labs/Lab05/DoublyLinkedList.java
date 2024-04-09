package Spring2024.CS220.Labs.Lab05;

public class DoublyLinkedList<E> implements Iterable<E> {

    private int size;
    private ListNode head, tail;

    public DoublyLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = head;
    }

    private class ListNode {
        private E data;
        private ListNode nextNode, prevNode;

        public ListNode(E e) {
            this.data = e;
            this.nextNode = null;
            this.prevNode = null;
        }

    }

    public E removeLast() {

        if (tail == null)
            return null;

        if (this.head == tail) {
            E toReturn = tail.data;
            head = null;
            tail = null;
            return toReturn;
        }

        E toReturn = tail.data;
        ListNode tempNode = tail;
        tail = tail.prevNode;
        tail.nextNode = null;
        tempNode.prevNode = null;
        return toReturn;

    }

    public E remove(int index) throws IndexOutOfBoundsException {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
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
            return toReturn;
        }

        else {

            ListNode curNode = head;

            for (int i = 0; i < index; i++) {
                curNode = curNode.nextNode;
            }

            // curNode is now at the desired index, given no sentinel nodes
            E toReturn = curNode.data;
            curNode.prevNode.nextNode = curNode.nextNode;
            curNode.nextNode.prevNode = curNode.prevNode;
            return toReturn;
        }
    }

}
