package org.bin2.matching;

import java.util.Comparator;

/**
 * Created by benoitroger on 19/01/15.
 */
public class TreeNode<K extends Comparable<K>,T> {
    private K key;
    private T value;

    private TreeNode<K,T> left;

    private TreeNode<K,T> right;

    public TreeNode(K key,T value) {
        this.value = value;
        this.key = key;
    }

    public K getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public TreeNode<K,T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<K,T> left) {
        this.left = left;
    }

    public TreeNode<K,T> getRight() {
        return right;
    }

    public void setRight(TreeNode<K,T> right) {
        this.right = right;
    }
}
