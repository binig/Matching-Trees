package org.bin2.matching.space;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Range;

import java.util.List;

/**
 * Created by benoitroger on 11/07/14.
 */
public class SpaceDefinition {
    private final ImmutableList<ContinuousAxisDefinition> axis;
    private final int numberOfDimension;
    private final int nbSplits;
    private final double maxValue;
    private final double minValue;
    private static class SplitDefinition {
        private final int split;
        private final int axis;

        private SplitDefinition(int split, int axis) {
            this.split = split;
            this.axis = axis;
        }

        public int getSplit() {
            return split;
        }

        public int getAxis() {
            return axis;
        }
    }

    public SpaceDefinition(List<ContinuousAxisDefinition> axis) {
        this.axis = ImmutableList.copyOf(axis);
        this.numberOfDimension = this.axis.size();
        this.nbSplits = Long.SIZE/numberOfDimension;
        double max=Double.MIN_VALUE;
        double min=Double.MAX_VALUE;
        for(ContinuousAxisDefinition a:axis) {
            max = Math.max(a.getMaxValue(), max);
            min = Math.min(a.getMinValue(),min);
        }
        this.maxValue = max;
        this.minValue = min;
    }

    public SpaceDefinition(ContinuousAxisDefinition[] axis) {
        this(ImmutableList.copyOf(axis));
    }

    public List<ContinuousAxisDefinition> getAxis() {
        return axis;
    }

    /**
     * if idx is store on long
     * one divison will need numberOfDimension bits
     * with 8 dimension 64 bit we can split 8 times
     * need to find some optimisation for boolean dimension ?
     * but lets start with a random distribution more or less continious
     *
     * OO_ O_O O_O
     */
    public long buildIndex(double[] coords) {
        long index=0;
        for (int dim=0;dim<this.numberOfDimension;dim++) {
            final ContinuousAxisDefinition axisDefinition=  axis.get(dim);
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
            }
        }
        return index;
    }

    public SplitDefinition[] getAxisIndexComposition() {
        SplitDefinition[] comp = new SplitDefinition[Long.SIZE];
        int axis =0;

        ContinuousAxisDefinition def = getAxis().get(axis);
        for (int i=0;i<comp.length;i++) {
            int limitAxis = axis + this.numberOfDimension;
            while (!hasSplit(def, axis)&&axis<limitAxis) {
                axis++;
                def = getAxis().get(axis);
            }
            int c= axis%this.numberOfDimension;
            comp[i] = new SplitDefinition(axis/this.numberOfDimension,c);
        }
        return comp;
    }
    private boolean hasSplit(ContinuousAxisDefinition def, int axis) {
        int split = axis/numberOfDimension;
        // we need to check if the split is kind of the same around the value
        //int ranges = 1<<(split+1);
        //for (int i=0;i<ranges;i+=2) {
        //
        //
        // }
        //TODO impl
        return true;
    }

    public Range<Double>[] fromIndex(long index) {
        Range<Double>[] ranges = new Range[this.numberOfDimension];
        byte[] found = new byte[Long.SIZE];
        for (int dim = 0; dim < this.numberOfDimension; dim++) {
            final ContinuousAxisDefinition axisDefinition=  axis.get(dim);
            double max = axisDefinition.getMaxValue();
            double min = axisDefinition.getMinValue();
            for (int i = 0; i < nbSplits; i++) {
                boolean up = (index& (1l<<((nbSplits-i-1)*numberOfDimension+dim)) ) !=0;
                found[((nbSplits-i-1)*numberOfDimension+dim)] = (byte) (up ?1:0);
                if(up) {
                   min =   min + (max-min)/2d;
                } else {
                   max =   min + (max-min)/2d;
                }
            }
            ranges[dim] = Range.closed(min,max);
        }
        return ranges;
    }
}
