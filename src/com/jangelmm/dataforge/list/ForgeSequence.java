package com.jangelmm.dataforge.list;

import com.jangelmm.dataforge.basics.Node;

/**
 * ForgeSequence is an unordered linked list that supports generic types.
 * It provides methods to insert, remove, search, and print elements in both ascending and descending order.
 *
 * @param <T> the type of elements in this list
 */
public class ForgeSequence<T extends Comparable<T>> {
    
    private Node<T> head, tail;

    /**
     * Constructs an empty ForgeSequence.
     */
    public ForgeSequence() {
        head = tail = null;
    }

    /**
     * Inserts an element at the end of the sequence and sorts the sequence.
     *
     * @param element the element to be added
     * @return true if the element was successfully inserted, false otherwise
     */
    public boolean insert(T element) {
        if (element == null) {
            return false;
        }
        Node<T> newNode = new Node<>(element, null);
        if (head == null) {  // Empty list
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
            sort();
        }
        return true;
    }

    /**
     * Sorts the sequence using the bubble sort algorithm.
     */
    private void sort() {
        for (Node<T> i = head; i != null; i = i.getNext()) {
            for (Node<T> j = i.getNext(); j != null; j = j.getNext()) {
                if (i.getData().compareTo(j.getData()) > 0) {
                    T temp = i.getData();
                    i.setData(j.getData());
                    j.setData(temp);
                }
            }
        }
    }

    /**
     * Removes and returns the first element in the sequence.
     *
     * @return the first element, or null if the sequence is empty
     */
    public T removeFirst() {
        T element = null;
        if (head != null) {  // Non-empty list
            element = head.getData();
            head = head.getNext();
            if (head == null) {  // The list becomes empty
                tail = null;
            }
        }
        return element;
    }

    /**
     * Removes and returns the last element in the sequence.
     *
     * @return the last element, or null if the sequence is empty
     */
    public T removeLast() {
        T element = null;
        if (head == null) {  // Empty list
            return element;
        }
        if (head == tail) {  // Single element in the list
            element = head.getData();
            head = tail = null;
        } else {  // More than one element
            Node<T> current = head;
            while (current.getNext() != tail) {
                current = current.getNext();
            }
            element = tail.getData();
            tail = current;
            tail.setNext(null);
        }
        return element;
    }

    /**
     * Removes and returns the element at the specified position in the sequence.
     *
     * @param position the position of the element to be removed
     * @return the element at the specified position, or null if the position is invalid
     */
    public T removeAt(int position) {
        T element = null;
        if (position < 0 || head == null) {  // Invalid position or empty list
            return element;
        }
        if (position == 0) {  // First element
            return removeFirst();
        }
        Node<T> current = head;
        int index = 1;
        while (current.getNext() != null && index < position) {
            current = current.getNext();
            index++;
        }
        if (current.getNext() != null) {
            element = current.getNext().getData();
            current.setNext(current.getNext().getNext());
            if (current.getNext() == null) {  // Last element was removed
                tail = current;
            }
        }
        return element;
    }

    /**
     * Prints the elements of the sequence in ascending order.
     */
    public void printAscending() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    /**
     * Prints the elements of the sequence in descending order.
     */
    public void printDescending(){
        printDescending(head);
    }
    /**
     * Auxiliary function to use printDecending()
     * @param current the current node to start the recursive printing 
     */
    private void printDescending(Node<T> current) {
        if (current != null) {
            printDescending(current.getNext());
            System.out.println(current.getData());
        }
    }
    
    /**
     * Searches for an element in the sequence.
     *
     * @param element the element to search for
     * @return true if the element is found, false otherwise
     */
    public boolean contains(T element) {
        Node<T> current = head;
        while (current != null) {
            if (current.getData().equals(element)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
}
