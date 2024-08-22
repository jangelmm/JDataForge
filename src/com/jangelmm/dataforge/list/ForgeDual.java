package com.jangelmm.dataforge.list;

/**
 * The {@code ForgeDual} class represents a doubly linked list that can store any type of data.
 * It supports operations to insert, remove, and search for elements within the list.
 *
 * @param <T> The type of data stored in the list.
 */
public class ForgeDual<T> {
    private DoublyNode<T> head, tail;

    /**
     * Constructs an empty doubly linked list.
     */
    public ForgeDual() {
        head = tail = null;
    }

    /**
     * Inserts a new element at the beginning of the list.
     *
     * @param data The data to insert.
     * @return {@code true} if the insertion was successful, {@code false} otherwise.
     */
    public boolean insertAtStart(T data) {
        if (data == null) {
            return false;
        }
        DoublyNode<T> newNode = new DoublyNode<>(data, null, head);
        if (newNode == null) {
            return false;
        }
        if (head == null) {
            head = tail = newNode;
        } else {
            head.setPrevious(newNode);
            head = newNode;
        }
        return true;
    }

    /**
     * Inserts a new element at the end of the list.
     *
     * @param data The data to insert.
     * @return {@code true} if the insertion was successful, {@code false} otherwise.
     */
    public boolean insertAtEnd(T data) {
        if (data == null) {
            return false;
        }
        DoublyNode<T> newNode = new DoublyNode<>(data, tail, null);
        if (newNode == null) {
            return false;
        }
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        return true;
    }

    /**
     * Inserts a new element after a specified reference element.
     *
     * @param data      The data to insert.
     * @param reference The reference element after which to insert the new data.
     * @return {@code true} if the insertion was successful, {@code false} otherwise.
     */
    public boolean insertAfter(T data, T reference) {
        if (data == null || reference == null || head == null) {
            return false;
        }
        DoublyNode<T> current = head;
        while (current != null) {
            if (current.getData().equals(reference)) {
                if (current.getNext() == null) {
                    return insertAtEnd(data);
                } else {
                    DoublyNode<T> newNode = new DoublyNode<>(data, current, current.getNext());
                    if (newNode == null) {
                        return false;
                    }
                    current.getNext().setPrevious(newNode);
                    current.setNext(newNode);
                    return true;
                }
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Inserts a new element before a specified reference element.
     *
     * @param data      The data to insert.
     * @param reference The reference element before which to insert the new data.
     * @return {@code true} if the insertion was successful, {@code false} otherwise.
     */
    public boolean insertBefore(T data, T reference) {
        if (data == null || reference == null || head == null) {
            return false;
        }
        if (reference.equals(head.getData())) {
            return insertAtStart(data);
        }
        DoublyNode<T> current = head.getNext();
        while (current != null) {
            if (current.getData().equals(reference)) {
                DoublyNode<T> newNode = new DoublyNode<>(data, current.getPrevious(), current);
                if (newNode == null) {
                    return false;
                }
                current.getPrevious().setNext(newNode);
                current.setPrevious(newNode);
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Prints the list elements in ascending order.
     */
    public void printListAscending() {
        DoublyNode<T> current = head;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    /**
     * Inserts a new element at a specific position in the list.
     *
     * @param position The position at which to insert the new element.
     * @param data     The data to insert.
     * @return {@code true} if the insertion was successful, {@code false} otherwise.
     */
    public boolean insertAtPosition(int position, T data) {
        if (position < 0 || data == null) {
            return false;
        }
        if (position == 0) {
            return insertAtStart(data);
        }
        int count = 0;
        DoublyNode<T> current = head;
        while (current != null) {
            if (count == position - 1) {
                DoublyNode<T> newNode = new DoublyNode<>(data, current, current.getNext());
                if (current.getNext() != null) {
                    current.getNext().setPrevious(newNode);
                } else {
                    tail = newNode;
                }
                current.setNext(newNode);
                return true;
            }
            count++;
            current = current.getNext();
        }
        return false;
    }

    /**
     * Removes the element at the start of the list.
     *
     * @return The data of the removed element, or {@code null} if the list is empty.
     */
    public T removeHead() {
        if (head == null) {
            return null;
        }
        T data = head.getData();
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.getNext();
            if (head != null) {
                head.setPrevious(null);
            }
        }
        return data;
    }

    /**
     * Removes the element at the end of the list.
     *
     * @return The data of the removed element, or {@code null} if the list is empty.
     */
    public T removeTail() {
        if (head == null) {
            return null;
        }
        T data = tail.getData();
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.getPrevious();
            if (tail != null) {
                tail.setNext(null);
            }
        }
        return data;
    }

    /**
     * Removes the first occurrence of the specified element.
     *
     * @param data The data to remove.
     * @return The removed data, or {@code null} if the element was not found.
     */
    public T removeElement(T data) {
        if (!contains(data)) {
            return null;
        }
        int pos = getPosition(data);
        return removeAtPosition(pos);
    }

    /**
     * Removes the element at a specific position in the list.
     *
     * @param position The position of the element to remove.
     * @return The data of the removed element, or {@code null} if the position is invalid.
     */
    public T removeAtPosition(int position) {
        if (position < 0 || head == null) {
            return null;
        }
        if (position == 0) {
            return removeHead();
        }
        int count = 0;
        DoublyNode<T> current = head;
        while (current != null) {
            if (count == position) {
                T data = current.getData();
                if (current == tail) {
                    return removeTail();
                } else {
                    current.getPrevious().setNext(current.getNext());
                    current.getNext().setPrevious(current.getPrevious());
                }
                return data;
            }
            count++;
            current = current.getNext();
        }
        return null;
    }

    /**
     * Returns the position of the specified element in the list.
     *
     * @param data The data to find.
     * @return The position of the data, or {@code -1} if the element was not found.
     */
    public int getPosition(T data) {
        int pos = 0;
        DoublyNode<T> current = head;
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
     * Checks if the specified element exists in the list.
     *
     * @param data The data to search for.
     * @return {@code true} if the element exists, {@code false} otherwise.
     */
    public boolean contains(T data) {
        DoublyNode<T> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
}
