package org.bin2.matching;

import java.util.Arrays;

/**
 * Created by benoitroger on 05/02/15.
 */
public class QuadtreeIndex implements Index<QuadtreeIndex> {
    public static final int orderInc = 4;

    double[] coordinates;
    byte[] index;
    int order;


    public void expendIndex(int order) {
        //TODO impl


        long index=0;
        for (int dim=0;dim<this.coordinates.length;dim++) {
           /* final ContinuousAxisDefinition axisDefinition=  axis.get(dim);
            final double coord = coords[dim];
            double max = axisDefinition.getMaxValue();
            double min = axisDefinition.getMinValue();
            double middle = min + (max-min)/2d;
            for (int i = 0; i < nbSplits; i++) {
                boolean b =coord>=middle;
                if (b) {
                    min = middle;
                } else {
                    max = middle;
                }
                middle = min + (max-min)/2d;
                index |= (b?1l:0l)<<((nbSplits-i-1)*numberOfDimension+dim);
            }*/
        }

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
    public byte[] getIndex() {
        return index;
    }

    @Override
    public int compareTo(QuadtreeIndex o) {
        return compareTo(o,true,40,false);
    }
}
