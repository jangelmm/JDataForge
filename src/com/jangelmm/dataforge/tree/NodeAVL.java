package com.jangelmm.dataforge.tree;

/**
 * Represents a node in an AVL tree, storing key-value pairs.
 *
 * @param <K> the type of keys maintained by this node
 * @param <V> the type of mapped values
 */
public class NodeAVL<K, V> {
    private K key;
    private V value;
    private NodeAVL<K, V> left;
    private NodeAVL<K, V> right;
    private int height;

    /**
     * Default constructor.
     */
    public NodeAVL() {}

    /**
     * Constructs a NodeAVL with the specified key and value.
     *
     * @param key   the key associated with the node
     * @param value the value associated with the node
     */
    public NodeAVL(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public NodeAVL<K, V> getLeft() {
        return left;
    }

    public void setLeft(NodeAVL<K, V> left) {
        this.left = left;
    }

    public NodeAVL<K, V> getRight() {
        return right;
    }

    public void setRight(NodeAVL<K, V> right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}