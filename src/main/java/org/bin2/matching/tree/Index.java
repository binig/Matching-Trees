package org.bin2.matching.tree;

/**
 * Created by benoitroger on 05/02/15.
 */
public interface Index<T>  extends Comparable<T> {
    int compareTo(T o, boolean autoExpendIndex, int maxOrder, boolean indexOnly);
    void expendIndex(int order);

    int innerValuesCompare(T o);
    int getOrder();

    byte[] getIndex();
}
