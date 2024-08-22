package com.jangelmm.dataforge.basics;

/**
 * A generic Node class used in linked data structures such as stacks, queues, and lists.
 *
 * @param <T> the type of the data stored in this Node.
 * @author jangelmm
 */
public class Node<T> {
    private T data;
    private Node<T> next;

    /**
     * Constructs a new Node with the given data and the reference to the next node.
     *
     * @param data the data to be stored in this node.
     * @param next the next node in the linked structure.
     */
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    /**
     * Gets the data stored in this node.
     *
     * @return the data stored in this node.
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the data stored in this node.
     *
     * @param data the data to be stored in this node.
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Gets the next node in the linked structure.
     *
     * @return the next node in the linked structure.
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Sets the next node in the linked structure.
     *
     * @param next the next node in the linked structure.
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
}
