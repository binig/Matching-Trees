package org.bin2.matching.tree;

/**
 * Created by benoi_000 on 2/5/2015.
 */
public class TreeSpec {
    public static final int DEFAULT_MAX_ORDER = 20;
    public static final int DEFAULT_INC_ORDER = 5;

    private final double[] max;
    private final double[] min;
    private final int maxOrder;
    private final int orderInc;

    public TreeSpec(double[] max, double[] min) {
        this(max, min, DEFAULT_MAX_ORDER, DEFAULT_INC_ORDER);
    }

    public TreeSpec(double[] max, double[] min, final int maxOrder, int orderInc) {
        this.max = max;
        this.min = min;
        this.maxOrder = maxOrder;
        this.orderInc = orderInc;
    }

    public double[] getMax() {
        return max;
    }

    public double[] getMin() {
        return min;
    }

    public int getMaxOrder() {
        return maxOrder;
    }

    public int getOrderInc() {
        return orderInc;
    }
}
