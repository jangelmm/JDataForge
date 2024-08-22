package com.jangelmm.dataforge.tree;

/**
 * A generic binary tree implementation.
 *
 * @param <T> the type of data stored in the tree nodes
 */
public class ForgeBinaryTree<T extends Comparable<T>> {
    private Node<T> root;

    /**
     * Constructs an empty ForgeBinaryTree.
     */
    public ForgeBinaryTree() {
        this.root = null;
    }

    /**
     * Checks if a value is present in the tree.
     *
     * @param value the value to search for
     * @return true if the value is found, false otherwise
     */
    public boolean contains(T value) {
        return contains(value, root);
    }

    private boolean contains(T value, Node<T> node) {
        if (node == null) {
            return false;
        }
        int cmp = value.compareTo(node.getData());
        if (cmp < 0) {
            return contains(value, node.getLeft());
        } else if (cmp > 0) {
            return contains(value, node.getRight());
        } else {
            return true;
        }
    }

    /**
     * Adds a value to the tree.
     *
     * @param value the value to add
     * @return true if the value was added, false if it was already present
     */
    public boolean add(T value) {
        if (value == null) {
            return false;
        }
        if (root == null) {
            root = new Node<>(value, null, null);
            return true;
        }
        return add(value, root);
    }

    private boolean add(T value, Node<T> node) {
        int cmp = value.compareTo(node.getData());
        if (cmp < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new Node<>(value, null, null));
                return true;
            } else {
                return add(value, node.getLeft());
            }
        } else if (cmp > 0) {
            if (node.getRight() == null) {
                node.setRight(new Node<>(value, null, null));
                return true;
            } else {
                return add(value, node.getRight());
            }
        } else {
            return false; // Value already exists
        }
    }

    /**
     * Removes a value from the tree.
     *
     * @param value the value to remove
     * @return true if the value was removed, false if it was not found
     */
    public boolean remove(T value) {
        if (value == null || root == null) {
            return false;
        }
        root = remove(value, root);
        return true;
    }

    private Node<T> remove(T value, Node<T> node) {
        if (node == null) {
            return null;
        }
        int cmp = value.compareTo(node.getData());
        if (cmp < 0) {
            node.setLeft(remove(value, node.getLeft()));
        } else if (cmp > 0) {
            node.setRight(remove(value, node.getRight()));
        } else {
            // Node with one child or no child
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            // Node with two children
            Node<T> minNode = findMin(node.getRight());
            node.setData(minNode.getData());
            node.setRight(remove(minNode.getData(), node.getRight()));
        }
        return node;
    }

    /**
     * Finds the minimum value in the subtree rooted at the given node.
     *
     * @param node the root of the subtree
     * @return the node with the minimum value
     */
    private Node<T> findMin(Node<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    /**
     * Performs an in-order traversal of the tree.
     */
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node<T> node) {
        if (node != null) {
            inOrderTraversal(node.getLeft());
            System.out.print(" " + node.getData());
            inOrderTraversal(node.getRight());
        }
    }

    /**
     * Performs a pre-order traversal of the tree.
     */
    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node<T> node) {
        if (node != null) {
            System.out.print(" " + node.getData());
            preOrderTraversal(node.getLeft());
            preOrderTraversal(node.getRight());
        }
    }

    /**
     * Performs a post-order traversal of the tree.
     */
    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    private void postOrderTraversal(Node<T> node) {
        if (node != null) {
            postOrderTraversal(node.getLeft());
            postOrderTraversal(node.getRight());
            System.out.print(" " + node.getData());
        }
    }

    /**
     * Counts the number of leaves in the tree.
     *
     * @return the number of leaves
     */
    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(Node<T> node) {
        if (node == null) {
            return 0;
        }
        if (node.getLeft() == null && node.getRight() == null) {
            return 1;
        }
        return countLeaves(node.getLeft()) + countLeaves(node.getRight());
    }

    /**
     * Counts the number of nodes in the tree.
     *
     * @return the number of nodes
     */
    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return countNodes(node.getLeft()) + countNodes(node.getRight()) + 1;
    }

    /**
     * Counts the number of parent nodes in the tree.
     *
     * @return the number of parent nodes
     */
    public int countParents() {
        return countNodes() - countLeaves();
    }

    /**
     * Removes all nodes from the tree, effectively clearing it.
     */
    public void clear() {
        root = null;
    }
    
    /**
     * Prints the tree in a graphical form, similar to the 'tree' command in
     * Linux.
     */
    public void printTree() {
        printTreeHelper(root, "", true);
    }

    private void printTreeHelper(Node<T> node, String prefix, boolean isTail) {
        if (node == null) {
            return;
        }

        // Print the current node
        System.out.println(prefix + (isTail ? "└── " : "├── ") + node.getData());

        // Print the left and right subtrees
        if (node.getLeft() != null || node.getRight() != null) {
            if (node.getRight() != null) {
                printTreeHelper(node.getRight(), prefix + (isTail ? "    " : "│   "), false);
            }
            if (node.getLeft() != null) {
                printTreeHelper(node.getLeft(), prefix + (isTail ? "    " : "│   "), true);
            }
        }
    }

    /**
     * Prints the tree in a more graphical form, like a sideways tree.
     */
    public void printGraphicalTree() {
        printGraphicalTreeHelper(root, 0);
    }

    private void printGraphicalTreeHelper(Node<T> node, int level) {
        if (node == null) {
            return;
        }

        // Print the right subtree first (so it appears on the right side)
        printGraphicalTreeHelper(node.getRight(), level + 1);

        // Print the current node
        printSpaces(level);
        System.out.println(node.getData());

        // Print the left subtree
        printGraphicalTreeHelper(node.getLeft(), level + 1);
    }

    /**
     * Prints a number of spaces corresponding to the level of the tree.
     */
    private void printSpaces(int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
    }
}
