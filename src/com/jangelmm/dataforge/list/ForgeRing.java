package com.jangelmm.dataforge.list;

import com.jangelmm.dataforge.basics.Node;

/**
 * ForgeRing is a circular singly linked list that supports generic types.
 * It provides methods to insert, remove, search, and print elements.
 *
 * @param <T> the type of elements in this list
 */
public class ForgeRing<T> {
    private Node<T> head;
    private Node<T> tail;

    /**
     * Constructs an empty ForgeRing.
     */
    public ForgeRing() {
        head = null;
        tail = null;
    }

    /**
     * Inserts an element at the beginning of the ring.
     *
     * @param element the element to be inserted
     * @return true if the element was successfully inserted, false otherwise
     */
    public boolean insertAtBeginning(T element) {
        if (element == null) {
            return false;
        }
        Node<T> newNode = new Node<>(element, head);
        if (head == null) { // When the list is empty
            head = tail = newNode;
            newNode.setNext(newNode);
        } else { // When there are existing nodes
            head = newNode;
            tail.setNext(head);
        }
        return true;
    }

    /**
     * Inserts an element at the end of the ring.
     *
     * @param element the element to be inserted
     * @return true if the element was successfully inserted, false otherwise
     */
    public boolean insertAtEnd(T element) {
        if (element == null) {
            return false;
        }
        if (head == null) { // If the list is empty
            return insertAtBeginning(element);
        }
        Node<T> newNode = new Node<>(element, head);
        tail.setNext(newNode);
        tail = newNode;
        return true;
    }

    /**
     * Removes and returns the first element in the ring.
     *
     * @return the first element, or null if the ring is empty
     */
    public T removeFirst() {
        if (head == null) { // If the ring is empty
            return null;
        }
        T data = head.getData();
        if (head == tail) { // If there's only one element
            head = tail = null;
        } else { // When there are more elements
            head = head.getNext();
            tail.setNext(head);
        }
        return data;
    }

    /**
     * Removes and returns the last element in the ring.
     *
     * @return the last element, or null if the ring is empty
     */
    public T removeLast() {
        if (head == null) { // If the ring is empty
            return null;
        }
        T data = tail.getData();
        if (head == tail) { // If there's only one element
            head = tail = null;
        } else { // When there are more elements
            Node<T> current = head;
            while (current.getNext() != tail) {
                current = current.getNext();
            }
            current.setNext(head);
            tail = current;
        }
        return data;
    }

    /**
     * Inserts an element at a specified position in the ring.
     *
     * @param element the element to be inserted
     * @param position the position where the element should be inserted
     * @return true if the element was successfully inserted, false otherwise
     */
    public boolean insertAtPosition(T element, int position) {
        if (position < 0 || element == null) {
            return false;
        }
        if (position == 0) {
            return insertAtBeginning(element);
        }
        Node<T> current = head;
        for (int i = 1; i < position && current.getNext() != head; i++) {
            current = current.getNext();
        }
        Node<T> newNode = new Node<>(element, current.getNext());
        current.setNext(newNode);
        if (newNode.getNext() == head) {
            tail = newNode;
        }
        return true;
    }

    /**
     * Removes and returns the element at a specified position in the ring.
     *
     * @param position the position of the element to be removed
     * @return the removed element, or null if the position is invalid or the ring is empty
     */
    public T removeAtPosition(int position) {
        if (position < 0 || head == null) {
            return null;
        }
        if (position == 0) {
            return removeFirst();
        }
        Node<T> current = head;
        Node<T> previous = tail;
        for (int i = 0; i < position && current != tail; i++) {
            previous = current;
            current = current.getNext();
        }
        if (current != tail) {
            previous.setNext(current.getNext());
            return current.getData();
        } else if (current == tail) {
            return removeLast();
        }
        return null;
    }

    /**
     * Inserts an element before a specified reference element in the ring.
     *
     * @param element the element to be inserted
     * @param reference the element before which the new element will be inserted
     * @return true if the element was successfully inserted, false otherwise
     */
    public boolean insertBefore(T element, T reference) {
        if (element == null || reference == null || head == null) {
            return false;
        }
        if (reference.equals(head.getData())) {
            return insertAtBeginning(element);
        }
        Node<T> current = head;
        Node<T> previous = tail;
        do {
            if (current.getData().equals(reference)) {
                Node<T> newNode = new Node<>(element, current);
                previous.setNext(newNode);
                return true;
            }
            previous = current;
            current = current.getNext();
        } while (current != head);
        return false;
    }

    /**
     * Removes and returns the element before a specified reference element in the ring.
     *
     * @param reference the element after which the element will be removed
     * @return the removed element, or null if the reference is invalid or the ring is empty
     */
    public T removeBefore(T reference) {
        if (reference == null || head == null || head.getData().equals(reference)) {
            return null;
        }
        Node<T> current = head.getNext();
        Node<T> previous = head;
        Node<T> prePrevious = tail;
        do {
            if (current.getData().equals(reference)) {
                T data = previous.getData();
                prePrevious.setNext(current);
                return data;
            }
            prePrevious = previous;
            previous = current;
            current = current.getNext();
        } while (current != head);
        return null;
    }

    /**
     * Inserts an element after a specified reference element in the ring.
     *
     * @param element the element to be inserted
     * @param reference the element after which the new element will be inserted
     * @return true if the element was successfully inserted, false otherwise
     */
    public boolean insertAfter(T element, T reference) {
        if (element == null || reference == null || head == null) {
            return false;
        }
        Node<T> current = head;
        do {
            if (current.getData().equals(reference)) {
                Node<T> newNode = new Node<>(element, current.getNext());
                current.setNext(newNode);
                if (current == tail) {
                    tail = newNode;
                }
                return true;
            }
            current = current.getNext();
        } while (current != head);
        return false;
    }

    /**
     * Prints all elements in the ring.
     */
    public void printData() {
        if (head == null) {
            System.out.println("No elements in the ring.");
        } else {
            Node<T> current = head;
            do {
                System.out.println(current.getData());
                current = current.getNext();
            } while (current != head);
        }
    }
}
