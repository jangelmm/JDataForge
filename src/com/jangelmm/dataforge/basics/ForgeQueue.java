package com.jangelmm.dataforge.basics;

/**
 * A generic queue implementation using a linked list structure.
 * 
 * @param <T> the type of elements held in this queue.
 */
public class ForgeQueue<T> {
    private Node<T> front, rear;

    /**
     * Constructs an empty queue.
     */
    public ForgeQueue() {
        front = rear = null; // The queue is initially empty
    }

    /**
     * Checks if the queue is empty.
     * 
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * Adds an element to the end of the queue.
     * 
     * @param element the element to be added to the queue.
     * @return true if the element was successfully added, false otherwise.
     */
    public boolean enqueue(T element) {
        Node<T> newNode = new Node<>(element, null);
        if (newNode == null) {
            return false; // Out of memory (unlikely in Java, but kept for consistency)
        }
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
        return true;
    }

    /**
     * Removes and returns the element at the front of the queue.
     * 
     * @return the element at the front of the queue, or null if the queue is empty.
     */
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        T element = front.getData();
        front = front.getNext();
        if (front == null) {
            rear = null; // Queue becomes empty
        }
        return element;
    }

    /**
     * Returns the element at the front of the queue without removing it.
     * 
     * @return the element at the front of the queue, or null if the queue is empty.
     */
    public T peekFront() {
        if (isEmpty()) {
            return null;
        }
        return front.getData();
    }

    /**
     * Returns the element at the rear of the queue without removing it.
     * 
     * @return the element at the rear of the queue, or null if the queue is empty.
     */
    public T peekRear() {
        if (isEmpty()) {
            return null;
        }
        return rear.getData();
    }
}
