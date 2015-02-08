package org.bin2.matching.tree;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by benoi_000 on 2/6/2015.
 */
public class QuadtreeIndexTest {

    @Test
    public void testDiffOrders() {
        double[] coord = new double[]{1, 2, 3};
        TreeSpec treeSpec = new TreeSpec(new double[]{10, 10, 10}, new double[]{0, 0, 0});
        QuadtreeIndex idx1 = new QuadtreeIndex(treeSpec, coord);
        QuadtreeIndex idx2 = new QuadtreeIndex(treeSpec, coord);
        idx1.expendIndex(4);
        idx1.expendIndex(128);
        idx2.expendIndex(4);
        Assert.assertEquals(idx1.compareTo(idx2), 0);
    }
}
