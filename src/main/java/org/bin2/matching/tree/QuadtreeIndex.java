package org.bin2.matching.tree;

import java.util.Arrays;

/**
 * Created by benoitroger on 05/02/15.
 */
public class QuadtreeIndex implements ComparableIndex<QuadtreeIndex> {

    private IndexConfiguration indexConfiguration;
    private double[] coordinates;

    private int[] index;
    private int order;

    public QuadtreeIndex(IndexConfiguration indexConfiguration, double[] coordinates) {
        this.indexConfiguration = indexConfiguration;
        this.coordinates = coordinates;
        expendIndex(indexConfiguration.getOrderInc());
    }

    public void expendIndex(int order) {
        if (order < this.order) return;
        int[] newIndex = new int[(int) Math.ceil(coordinates.length * order / ((double) Integer.SIZE))];
        for (int dim=0;dim<this.coordinates.length;dim++) {
            final double coord = this.coordinates[dim];
            double max = indexConfiguration.getMax()[dim];
            double min = indexConfiguration.getMin()[dim];
            double middle = min + (max-min)/2d;
            for (int i = 0; i < order; i++) {
                boolean b =coord>=middle;
                if (b) {
                    min = middle;
                } else {
                    max = middle;
                }
                middle = min + (max-min)/2d;
                int bitPos = i * coordinates.length + dim;
                int idx = bitPos / Integer.SIZE;
                int localBitPos = Integer.SIZE - 1 - bitPos % Integer.SIZE;
                newIndex[idx] |= (b?1:0)<<localBitPos;
            }
        }
        this.index=newIndex;
        this.order = order;

    }

    @Override
    public int compareTo(QuadtreeIndex o, boolean autoExpendIndex, int maxOrder, boolean indexOnly) {
        return IndexUtils.compare(this, o, autoExpendIndex, maxOrder, indexConfiguration.getOrderInc(), indexOnly);
    }

    @Override
    public int innerValuesCompare(QuadtreeIndex o) {
        return IndexUtils.innerValuesCompare(this.coordinates, o.coordinates);
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public int[] getIndex() {
        return index;
    }

    @Override
    public int compareTo(QuadtreeIndex o) {
        return compareTo(o, true, indexConfiguration.getMaxOrder(), false);
    }

    @Override
    public int getNumberOfSignificantBits(int order) {
        return coordinates.length * order;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        if (index != null)
            for (int i : index) {
                b.append(Integer.toHexString(i));
            }

        return b.toString() + Arrays.toString(coordinates);
    }
}
