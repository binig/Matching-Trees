package org.bin2.matching.tree;

/**
 * Created by benoitroger on 05/02/15.
 */
public class QuadtreeIndex implements ComparableIndex<QuadtreeIndex> {
    private TreeSpec treeSpec;
    private double[] coordinates;

    private int[] index;
    private int order;

    public QuadtreeIndex(TreeSpec treeSpec, double[] coordinates) {
        this.treeSpec = treeSpec;
        this.coordinates = coordinates;
    }

    public void expendIndex(int order) {
        int[] newIndex = new int[(int) Math.ceil(coordinates.length * order / ((double) Integer.SIZE))];
        for (int dim=0;dim<this.coordinates.length;dim++) {
            final double coord = this.coordinates[dim];
            double max = treeSpec.getMax()[dim];
            double min =  treeSpec.getMin()[dim];
            double middle = min + (max-min)/2d;
            for (int i = 0; i < order; i++) {
                boolean b =coord>=middle;
                if (b) {
                    min = middle;
                } else {
                    max = middle;
                }
                middle = min + (max-min)/2d;
                int bitPos = ((order-i-1)*coordinates.length+dim);
                int idx = bitPos / Integer.SIZE;
                int localBitPos = bitPos % Integer.SIZE;
                newIndex[idx] |= (b?1:0)<<localBitPos;
            }
        }
        this.index=newIndex;
        this.order = order;

    }

    @Override
    public int compareTo(QuadtreeIndex o, boolean autoExpendIndex, int maxOrder, boolean indexOnly) {
        return IndexUtils.compare(this, o, autoExpendIndex, maxOrder, indexOnly);
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
        return compareTo(o,true,40,false);
    }

    @Override
    public int getNumberOfSignificantBits(int order) {
        return coordinates.length * order;
    }
}
