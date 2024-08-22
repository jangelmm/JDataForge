package com.jangelmm.dataforge.tree;

/**
 * A generic class representing a node in a binary tree.
 *
 * @param <T> the type of data stored in the node
 */
public class Node<T> {
    private T data;
    private Node<T> left, right;

    /**
     * Constructs a new Node with the given data and child nodes.
     *
     * @param data the data to be stored in the node
     * @param left the left child node
     * @param right the right child node
     */
    public Node(T data, Node<T> left, Node<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    /**
     * Returns the data stored in the node.
     *
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the data stored in the node.
     *
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Returns the left child node.
     *
     * @return the left child node
     */
    public Node<T> getLeft() {
        return left;
    }

    /**
     * Sets the left child node.
     *
     * @param left the left child node to set
     */
    public void setLeft(Node<T> left) {
        this.left = left;
    }

    /**
     * Returns the right child node.
     *
     * @return the right child node
     */
    public Node<T> getRight() {
        return right;
    }

    /**
     * Sets the right child node.
     *
     * @param right the right child node to set
     */
    public void setRight(Node<T> right) {
        this.right = right;
    }
}