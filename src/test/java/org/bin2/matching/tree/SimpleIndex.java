package org.bin2.matching.tree;

/**
 * Created by benoi_000 on 2/7/2015.
 */
public class SimpleIndex implements Index<SimpleIndex> {
    private final int index;

    public SimpleIndex(int index) {
        this.index = index;
    }

    @Override
    public int compareTo(SimpleIndex o) {
        return Integer.compare(index, o.index);
    }

    @Override
    public String toString() {
        return "{" +
                index +
                '}';
    }

    public int getIndex() {
        return index;
    }
}
