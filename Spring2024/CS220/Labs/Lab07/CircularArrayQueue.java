package Spring2024.CS220.Labs.Lab07;

public class CircularArrayQueue<T> {
    private int capacity;
    private Object[] array;
    private int size;
    private int front; // Index of the front element
    private int rear; // Index of the rear element

    public CircularArrayQueue(int capacity) {
        this.capacity = capacity;
        this.array = new Object[capacity];
        this.size = 0;
        this.front = 0;
        this.rear = -1; // Initially, no rear element
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    public void enqueue(T item) {
        if (isFull()) {
            expandArray();
        }
        rear = (rear + 1) % capacity;
        array[rear] = item;
        size++;
    }

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