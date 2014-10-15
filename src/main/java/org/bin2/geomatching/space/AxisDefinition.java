package org.bin2.geomatching.space;

import com.google.common.collect.ImmutableList;

/**
 * Created by benoitroger on 11/07/14.
 */
public class AxisDefinition {
    private final double maxValue;
    private final double minValue;
    private final Object[] values;
    private final Double[] weights;

    public AxisDefinition(double maxValue, double minValue) {
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.values=null;
        this.weights=null;
    }

    public AxisDefinition(Double[] weights, Object[] values) {
        this.weights = weights;
        this.values = values;
        this.maxValue=-1;
        this.minValue=-1;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public ImmutableList<Object> getValues() {
        return ImmutableList.copyOf( values);
    }

    public ImmutableList<Double> getWeights() {
        return  ImmutableList.copyOf(weights);
    }
}
