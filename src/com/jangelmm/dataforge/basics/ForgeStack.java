package com.jangelmm.dataforge.basics;

/**
 * A class representing a stack implemented using a singly linked list.
 * This stack supports standard operations such as push, pop, peek, and traversal.
 */
public class ForgeStack<T> {
    private Node<T> top;

    /**
     * Constructs an empty ForgeStack.
     */
    public ForgeStack() {
        top = null;
    }

    /**
     * Pushes an element onto the stack.
     *
     * @param element the element to be pushed onto the stack.
     * @return true if the element was successfully pushed, false otherwise.
     */
    public boolean push(T element) {
        Node<T> newNode = new Node<>(element, top);
        if (newNode == null) { // This check is redundant in Java, but kept for translation accuracy
            return false;
        } else {
            top = newNode;
            return true;
        }
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise.
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the element at the top of the stack, or null if the stack is empty.
     */
    public T peek() {
        if (!isEmpty()) {
            return top.getData();
        }
        return null;
    }

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the element removed from the top of the stack, or null if the stack is empty.
     */
    public T pop() {
        if (!isEmpty()) {
            T element = top.getData();
            top = top.getNext();
            return element;
        }
        return null;
    }

    /**
     * Traverses and prints the elements in the stack from top to bottom.
     */
    public void traverseStack() {
        Node<T> currentNode = top;
        while (currentNode != null) {
            System.out.println(currentNode.getData());
            currentNode = currentNode.getNext();  // Move to the next node
        }
    }
}

