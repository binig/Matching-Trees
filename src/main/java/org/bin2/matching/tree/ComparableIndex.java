package org.bin2.matching.tree;

/**
 * Created by benoitroger on 05/02/15.
 * method to implements so can use IndexUtils.compare
 *
 * @see org.bin2.matching.tree.IndexUtils#compare(ComparableIndex, ComparableIndex, boolean, int, boolean)
 */
public interface ComparableIndex<T> extends Index<T> {
    int compareTo(T o, boolean autoExpendIndex, int maxOrder, boolean indexOnly);

    void expendIndex(int order);

    int innerValuesCompare(T o);

    int getOrder();

    int[] getIndex();

    int getNumberOfSignificantBits(int order);
}
