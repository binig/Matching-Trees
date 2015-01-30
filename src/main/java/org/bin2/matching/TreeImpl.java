package org.bin2.matching;

import java.util.Comparator;

/**
 * Basic binary tree impl
 */
public class TreeImpl<K extends Comparable<K>,T> {

    TreeNode<K,T> root;

    public void put(K key,T value) {
        root = put(root, key, value);
    }

    public T get(K key) {
        return get(root,key);
    }

    private T get(TreeNode<K, T> node, K key) {
        if (node == null) return null;
        int comp = node.getKey().compareTo(key);
        if (comp==0) return node.getValue();
        return get(comp<0?node.getRight():node.getLeft(),key);
    }

    private TreeNode<K,T> put(TreeNode<K,T> node,K key, T value) {

        if (node==null) return new TreeNode<>(key,value);
        int comp = node.getKey().compareTo(key);
        if (comp==0) {
            node.setValue(value);
        } else  if(comp<0)  {
            node.setRight(put(node.getRight(), key,value));
        } else {
            node.setLeft(put(node.getLeft(),key,value));
        }
        return node;
    }
}