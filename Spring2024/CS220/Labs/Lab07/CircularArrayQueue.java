package Spring2024.CS220.Labs.Lab07;

/*
 * CircularArrayQueue class to implement a queue using a circular array.
 * The queue has a fixed capacity, and it expands when the capacity is reached.
 * 
 * @param <T> The type of elements stored in the queue
 * @author Marcus Clements
 * @since 2024-05-01
 */
public class CircularArrayQueue<T> {
    private int capacity;
    private Object[] array;
    private int size;
    private int front; // Index of the front element
    private int rear; // Index of the rear element

    /**
     * Constructor to create a CircularArrayQueue with the specified capacity.
     * 
     * @param capacity The initial capacity of the queue
     */
    public CircularArrayQueue(int capacity) {
        this.capacity = capacity;
        this.array = new Object[capacity];
        this.size = 0;
        this.front = 0;
        this.rear = -1; // Initially, no rear element
    }

    /**
     * Checks if the queue is empty.
     * 
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if the queue is full.
     * 
     * @return true if the queue is full, false otherwise
     */
    public boolean isFull() {
        return size == capacity;
    }

    /**
     * Returns the number of elements in the queue.
     * 
     * @return The number of elements in the queue
     */
    public int size() {
        return size;
    }

    /**
     * Adds an element to the rear of the queue.
     * 
     * @param item The element to add
     */
    public void enqueue(T item) {
        if (isFull()) {
            expandArray();
        }
        rear = (rear + 1) % capacity;
        array[rear] = item;
        size++;
    }

    /**
     * Removes and returns the element at the front of the queue.
     * 
     * @return The element at the front of the queue
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        @SuppressWarnings("unchecked")
        T item = (T) array[front];
        array[front] = null; // Clearing the dequeued element
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    /**
     * Expands the array when the capacity is reached.
     */
    private void expandArray() {
        int newCapacity = capacity * 2;
        Object[] newArray = new Object[newCapacity];
        // Copy elements to the new array
        for (int i = 0; i < size; i++) {
            newArray[i] = array[(front + i) % capacity];
        }
        array = newArray;
        capacity = newCapacity;
        front = 0;
        rear = size - 1;
    }
}