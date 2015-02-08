package org.bin2.matching.tree;

/**
 * Created by benoitroger on 19/01/15.
 */
public class TreeNode<K extends Comparable<K>,T> {
    public static final boolean RED = true;
    public static final boolean BLACK = false;

    private K key;
    private T value;


    private TreeNode<K,T> left;
    private TreeNode<K,T> right;

    private boolean color;
    private int count;


    public TreeNode(K key, T value, boolean color, int count) {
        this.value = value;
        this.key = key;
        this.color = color;
        this.count = count;
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

    public void setColor(boolean color) {
        this.color = color;
    }

    public void setSize(int count) {
        this.count = count;
    }

    public int size() {
        return count;
    }

    public boolean isRed() {
        return color == RED;
    }

    public boolean getColor() {
        return color;
    }

    public void setKey(K key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "TN{" +
                key +
                //  ", value=" + value +
                ", l=" + left +
                ", r=" + right +
                '}';
    }
}
