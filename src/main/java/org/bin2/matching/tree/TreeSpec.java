package org.bin2.matching.tree;

/**
 * Created by benoi_000 on 2/5/2015.
 */
public class TreeSpec {
    private final double[] max;
    private final double[] min;

    public TreeSpec(double[] max, double[] min) {
        this.max = max;
        this.min = min;
    }

    public double[] getMax() {
        return max;
    }

    public double[] getMin() {
        return min;
    }
}
