package org.bin2.geomatching.space;

import com.google.common.collect.ImmutableList;

/**
 * Created by benoitroger on 11/07/14.
 */
public class ContinuousAxisDefinition implements Normalizer,AxisDefinition {
    private final double maxValue;
    private final double minValue;


    public ContinuousAxisDefinition(double maxValue, double minValue) {
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    public ContinuousAxisDefinition(Double[] weights, Object[] values) {
        this.maxValue=-1;
        this.minValue=-1;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public double getMinValue() {
        return minValue;
    }

    @Override
    public Normalizer getNormalizer() {
        return this;
    }

    @Override
    public double normalize(double value) {
        return  (value-minValue)/maxValue;
    }

    @Override
    public double unormalize(double value) {
        return value*maxValue+minValue;
    }
}
