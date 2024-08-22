package com.jangelmm.dataforge.list;

/**
 * ForgeChain represents a singly linked list that allows for various operations
 * such as adding, removing, and searching for elements.
 *
 * @param <T> the type of elements in this list
 */
public class ForgeChain<T> {
    private Node<T> head, tail;

    /**
     * Constructs an empty ForgeChain.
     */
    public ForgeChain() {
        head = tail = null;
    }

    /**
     * Adds an element to the beginning of the list.
     *
     * @param data the element to add
     * @return true if the element was added, false if the element is null
     */
    public boolean addFirst(T data) {
        if (data == null) {
            return false;
        }
        Node<T> newNode = new Node<>(data, null);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        return true;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param data the element to add
     * @return true if the element was added, false if the element is null
     */
    public boolean addLast(T data) {
        if (data == null) {
            return false;
        }
        Node<T> newNode = new Node<>(data, null);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        return true;
    }

    /**
     * Removes and returns the first element of the list.
     *
     * @return the first element, or null if the list is empty
     */
    public T removeFirst() {
        if (head == null) {
            return null;
        }
        T data = head.getData();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        return data;
    }

    /**
     * Removes and returns the last element of the list.
     *
     * @return the last element, or null if the list is empty
     */
    public T removeLast() {
        if (head == null) {
            return null;
        }
        if (head == tail) {
            T data = head.getData();
            head = tail = null;
            return data;
        }
        Node<T> current = head;
        while (current.getNext() != tail) {
            current = current.getNext();
        }
        T data = tail.getData();
        tail = current;
        tail.setNext(null);
        return data;
    }

    /**
     * Removes and returns the element after the specified reference element.
     *
     * @param reference the reference element
     * @return the element after the reference, or null if not found or invalid
     */
    public T removeAfter(T reference) {
        if (reference == null || head == null) {
            return null;
        }
        Node<T> current = head;
        while (current != null && current.getNext() != null) {
            if (reference.equals(current.getData())) {
                T data = current.getNext().getData();
                current.setNext(current.getNext().getNext());
                if (current.getNext() == null) {
                    tail = current;
                }
                return data;
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * Traverses and prints each element in the list.
     */
    public void traverse() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    /**
     * Removes and returns the element at the specified position.
     *
     * @param pos the position of the element to remove
     * @return the element at the specified position, or null if invalid
     */
    public T removeAt(int pos) {
        if (pos < 0 || head == null) {
            return null;
        }
        if (pos == 0) {
            return removeFirst();
        }
        Node<T> current = head;
        for (int i = 1; i < pos; i++) {
            if (current.getNext() == null) {
                return null;
            }
            current = current.getNext();
        }
        return removeAfter(current.getData());
    }

    /**
     * Adds an element before the specified reference element.
     *
     * @param data the element to add
     * @param reference the reference element
     * @return true if the element was added, false otherwise
     */
    public boolean addBefore(T data, T reference) {
        if (data == null || reference == null || head == null) {
            return false;
        }
        if (head.getData().equals(reference)) {
            return addFirst(data);
        }
        Node<T> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().equals(reference)) {
                Node<T> newNode = new Node<>(data, current.getNext());
                current.setNext(newNode);
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Adds an element after the specified reference element.
     *
     * @param data the element to add
     * @param reference the reference element
     * @return true if the element was added, false otherwise
     */
    public boolean addAfter(T data, T reference) {
        if (data == null || reference == null || head == null) {
            return false;
        }
        Node<T> current = head;
        while (current != null) {
            if (current.getData().equals(reference)) {
                Node<T> newNode = new Node<>(data, current.getNext());
                current.setNext(newNode);
                if (newNode.getNext() == null) {
                    tail = newNode;
                }
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Searches for an element in the list.
     *
     * @param data the element to search for
     * @return true if the element is found, false otherwise
     */
    public boolean contains(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Adds an element in order without duplicates.
     *
     * @param data the element to add
     * @return true if the element was added, false otherwise
     */
    public boolean addSortedUnique(T data) {
        if (contains(data) || data == null) {
            return false;
        }
        if (head == null || ((Comparable<T>) data).compareTo(head.getData()) < 0) {
            return addFirst(data);
        } else {
            Node<T> prev = head;
            Node<T> current = head.getNext();
            while (current != null && ((Comparable<T>) current.getData()).compareTo(data) < 0) {
                prev = current;
                current = current.getNext();
            }
            Node<T> newNode = new Node<>(data, current);
            prev.setNext(newNode);
            if (current == null) {
                tail = newNode;
            }
            return true;
        }
    }

    /**
     * Returns the position of an element in the list.
     *
     * @param data the element to find
     * @return the position of the element, or -1 if not found
     */
    public int indexOf(T data) {
        int pos = 0;
        Node<T> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                return pos;
            }
            pos++;
            current = current.getNext();
        }
        return -1;
    }

    /**
     * Removes the specified element from the list.
     *
     * @param data the element to remove
     * @return the removed element, or null if not found
     */
    public T remove(T data) {
        int pos = indexOf(data);
        if (pos == -1) {
            return null;
        }
        return removeAt(pos);
    }

    /**
     * Adds an element at the specified position.
     *
     * @param pos the position to add the element at
     * @param data the element to add
     * @return true if the element was added, false otherwise
     */
    public boolean addAt(int pos, T data) {
        if (pos < 0 || data == null) {
            return false;
        }
        if (pos == 0) {
            return addFirst(data);
        }
        Node<T> current = head;
        for (int i = 1; i < pos; i++) {
            if (current.getNext() == null) {
                return false;
            }
            current = current.getNext();
        }
        Node<T> newNode = new Node<>(data, current.getNext());
        current.setNext(newNode);
        if (newNode.getNext() == null) {
            tail = newNode;
        }
        return true;
    }

    /**
     * Returns the node before the specified element.
     *
     * @param data the element to find the previous node of
     * @return the previous node, or null if not found or invalid
     */
    public Node<T> getPreviousNode(T data) {
        if (data == null || head == null || head.getData().equals(data)) {
            return null;
        }
        Node<T> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().equals(data)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * Reverses the order of elements in the list.
     */
    public void reverse() {
        if (head == null || head.getNext() == null) {
            return;
        }
        Node<T> prev = null;
        Node<T> current = head;
        Node<T> next;
        tail = head;
        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        head = prev;
    }

    /**
     * Clears all elements from the list.
     */
    public void clear() {
        head = tail = null;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Node represents a single element in the list.
     *
     * @param <T> the type of element held by the Node
     */
    private static class Node<T> {
        private T data;
        private Node<T> next;

        /**
         * Constructs a Node with specified data and next node.
         *
         * @param data the data for the node
         * @param next the next node in the list
         */
        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
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
         * Returns the next node.
         *
         * @return the next node
         */
        public Node<T> getNext() {
            return next;
        }

        /**
         * Sets the next node.
         *
         * @param next the next node to set
         */
        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
