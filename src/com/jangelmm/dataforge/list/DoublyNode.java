package com.jangelmm.dataforge.list;

/**
 * The `DoublyNode` class represents a node in a doubly linked list.
 * It stores a generic data element, along with references to the previous and next nodes in the list.
 *
 * @param <T> The type of data stored in the node.
 */
public class DoublyNode<T> {
    private T data;
    private DoublyNode<T> previous;
    private DoublyNode<T> next;

    /**
     * Constructs a new `DoublyNode` with the specified data, previous node, and next node.
     *
     * @param data The data element to be stored in the node.
     * @param previous The previous node in the linked list.
     * @param next The next node in the linked list.
     */
    public DoublyNode(T data, DoublyNode<T> previous, DoublyNode<T> next) {
        this.data = data;
        this.previous = previous;
        this.next = next;
    }

    /**
     * Returns the data stored in this node.
     *
     * @return The data stored in this node.
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the data stored in this node.
     *
     * @param data The data to be stored in this node.
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Returns the previous node in the linked list.
     *
     * @return The previous node.
     */
    public DoublyNode<T> getPrevious() {
        return previous;
    }

    /**
     * Sets the previous node in the linked list.
     *
     * @param previous The previous node.
     */
    public void setPrevious(DoublyNode<T> previous) {
        this.previous = previous;
    }

    /**
     * Returns the next node in the linked list.
     *
     * @return The next node.
     */
    public DoublyNode<T> getNext() {
        return next;
    }

    /**
     * Sets the next node in the linked list.
     *
     * @param next The next node.
     */
    public void setNext(DoublyNode<T> next) {
        this.next = next;
    }
}
