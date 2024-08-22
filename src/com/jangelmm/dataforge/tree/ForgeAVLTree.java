package com.jangelmm.dataforge.tree;

/**
 * A class representing a balanced AVL tree.
 *
 * @param <K> the type of keys maintained by this tree
 * @param <V> the type of mapped values
 */
public class ForgeAVLTree<K extends Comparable<K>, V> {
    private NodeAVL<K, V> root;

    /**
     * Default constructor. Initializes an empty AVL tree.
     */
    public ForgeAVLTree() {
        root = null;
    }

    /**
     * Searches for a node with the specified key.
     *
     * @param key the key to search for
     * @return the node containing the key, or null if not found
     */
    public NodeAVL<K, V> search(K key) {
        NodeAVL<K, V> current = root;
        while (current != null && !current.getKey().equals(key)) {
            if (current.getKey().compareTo(key) > 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return current;
    }

    /**
     * Inserts a key-value pair into the AVL tree.
     *
     * @param key   the key to insert
     * @param value the value to insert
     * @return true if insertion is successful, false if the key already exists
     */
    public boolean insert(K key, V value) {
        NodeAVL<K, V> newNode = new NodeAVL<>(key, value);
        if (root == null) {
            root = newNode;
            return true;
        }
        NodeAVL<K, V> insertedNode = insertAVL(newNode, root);
        if (insertedNode == null) return false;
        root = insertedNode;
        return true;
    }

    /**
     * Helper method for AVL insertion.
     */
    private NodeAVL<K, V> insertAVL(NodeAVL<K, V> newNode, NodeAVL<K, V> subtree) {
        NodeAVL<K, V> newParent = subtree;
        if (newNode.getKey().compareTo(subtree.getKey()) < 0) {
            if (subtree.getLeft() == null) {
                subtree.setLeft(newNode);
            } else {
                subtree.setLeft(insertAVL(newNode, subtree.getLeft()));
                if (height(subtree.getLeft()) - height(subtree.getRight()) == 2) {
                    if (newNode.getKey().compareTo(subtree.getLeft().getKey()) < 0) {
                        newParent = rotateLeft(subtree);
                    } else {
                        newParent = doubleRotateLeft(subtree);
                    }
                }
            }
        } else if (newNode.getKey().compareTo(subtree.getKey()) > 0) {
            if (subtree.getRight() == null) {
                subtree.setRight(newNode);
            } else {
                subtree.setRight(insertAVL(newNode, subtree.getRight()));
                if (height(subtree.getRight()) - height(subtree.getLeft()) == 2) {
                    if (newNode.getKey().compareTo(subtree.getRight().getKey()) > 0) {
                        newParent = rotateRight(subtree);
                    } else {
                        newParent = doubleRotateRight(subtree);
                    }
                }
            }
        } else {
            System.out.println("Duplicate key");
            return null;
        }

        subtree.setHeight(Math.max(height(subtree.getLeft()), height(subtree.getRight())) + 1);
        return newParent;
    }

    /**
     * Performs an in-order traversal of the tree.
     */
    public void inOrderTraversal() {
        inOrderTraversalHelper(root);
        System.out.println();
    }

    private void inOrderTraversalHelper(NodeAVL<K, V> node) {
        if (node != null) {
            inOrderTraversalHelper(node.getLeft());
            System.out.print("  " + node.getKey());
            inOrderTraversalHelper(node.getRight());
        }
    }

    /**
     * Performs a post-order traversal of the tree.
     */
    public void postOrderTraversal() {
        postOrderTraversalHelper(root);
    }

    private void postOrderTraversalHelper(NodeAVL<K, V> node) {
        if (node != null) {
            postOrderTraversalHelper(node.getLeft());
            postOrderTraversalHelper(node.getRight());
            System.out.print("  " + node.getKey());
        }
    }

    /**
     * Performs a pre-order traversal of the tree.
     */
    public void preOrderTraversal() {
        preOrderTraversalHelper(root);
    }

    private void preOrderTraversalHelper(NodeAVL<K, V> node) {
        if (node != null) {
            System.out.print("  " + node.getKey());
            preOrderTraversalHelper(node.getLeft());
            preOrderTraversalHelper(node.getRight());
        }
    }

    /**
     * Calculates the size of the tree.
     *
     * @return the number of nodes in the tree
     */
    public int size() {
        return sizeHelper(root);
    }

    private int sizeHelper(NodeAVL<K, V> node) {
        if (node != null) return (1 + sizeHelper(node.getLeft()) + sizeHelper(node.getRight()));
        return 0;
    }

    /**
     * Deletes a node with the specified key from the tree.
     *
     * @param key  the key of the node to delete
     * @param mode the mode of deletion (not currently used)
     * @return true if deletion is successful, false if the key is not found
     */
    public boolean delete(K key, int mode) {
        NodeAVL<K, V> current = root, parent = null;
        while (current != null && !current.getKey().equals(key)) {
            parent = current;
            if (current.getKey().compareTo(key) > 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        if (current == null) return false;
        if (current == root) root = deleteNode(current, mode);
        else {
            if (parent.getLeft() == current) parent.setLeft(deleteNode(current, mode));
            else parent.setRight(deleteNode(current, mode));
        }

        if (parent == null) return true;
        parent.setHeight(Math.max(height(parent.getLeft()), height(parent.getRight())) + 1);
        return true;
    }

    private NodeAVL<K, V> deleteNode(NodeAVL<K, V> node, int mode) {
        if (node.getLeft() == null) {
            return node.getRight();
        } else if (node.getRight() == null) {
            return node.getLeft();
        } else {
            NodeAVL<K, V> successor = node.getRight(), successorParent = null;
            while (successor.getLeft() != null) {
                successorParent = successor;
                successor = successor.getLeft();
            }
            node.setKey(successor.getKey());
            if (mode == 1) successorParent.setLeft(successor.getRight());
            else node.setRight(successor.getRight());
            return node;
        }
    }

    /**
     * Gets the key of the root node.
     *
     * @return the key of the root node
     */
    public K getRoot() {
        return root.getKey();
    }

    /**
     * Rotates the subtree to the left.
     *
     * @param node the root of the subtree
     * @return the new root of the subtree
     */
    private NodeAVL<K, V> rotateLeft(NodeAVL<K, V> node) {
        NodeAVL<K, V> leftChild = node.getLeft();
        node.setLeft(leftChild.getRight());
        leftChild.setRight(node);
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        leftChild.setHeight(Math.max(height(leftChild.getLeft()), height(leftChild.getRight())) + 1);
        return leftChild;
    }

    /**
     * Rotates the subtree to the right.
     *
     * @param node the root of the subtree
     * @return the new root of the subtree
     */
    private NodeAVL<K, V> rotateRight(NodeAVL<K, V> node) {
        NodeAVL<K, V> rightChild = node.getRight();
        node.setRight(rightChild.getLeft());
        rightChild.setLeft(node);
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        rightChild.setHeight(Math.max(height(rightChild.getLeft()), height(rightChild.getRight())) + 1);
        return rightChild;
    }

    /**
     * Performs a double left rotation.
     *
     * @param node the root of the subtree
     * @return the new root of the subtree
     */
    private NodeAVL<K, V> doubleRotateLeft(NodeAVL<K, V> node) {
        node.setLeft(rotateRight(node.getLeft()));
        return rotateLeft(node);
    }

    /**
     * Performs a double right rotation.
     *
     * @param node the root of the subtree
     * @return the new root of the subtree
     */
    private NodeAVL<K, V> doubleRotateRight(NodeAVL<K, V> node) {
        node.setRight(rotateLeft(node.getRight()));
        return rotateRight(node);
    }

    /**
     * Calculates the height of a node.
     *
     * @param node the node to calculate the height for
     * @return the height of the node, or -1 if the node is null
     */
    private int height(NodeAVL<K, V> node) {
        return node == null ? -1 : node.getHeight();
    }
    
    /**
    * Prints the tree in a graphical form, similar to the 'tree' command in Linux.
    */
    public void printTree() {
        printTreeHelper(root, "", true);
    }

    private void printTreeHelper(NodeAVL<K, V> node, String prefix, boolean isTail) {
        if (node == null) {
            return;
        }

        // Print the current node
        System.out.println(prefix + (isTail ? "└── " : "├── ") + node.getKey());

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

   private void printGraphicalTreeHelper(NodeAVL<K, V> node, int level) {
       if (node == null) {
           return;
       }

       // Print the right subtree first (so it appears on the right side)
       printGraphicalTreeHelper(node.getRight(), level + 1);

       // Print the current node
       printSpaces(level);
       System.out.println(node.getKey());

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
