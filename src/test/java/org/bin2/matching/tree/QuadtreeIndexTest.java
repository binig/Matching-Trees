package org.bin2.matching.tree;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;

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


    @Test
    public void testDiffOrders2() {
        double[][] coord = new double[][]{{0.4054175497278485, 0.28507660055555484, 0.2336113690806645}
                , {0.40066973024531627, 0.2927455088724463, 0.23923691050410156}};
        TreeSpec treeSpec = new TreeSpec(new double[]{1, 1, 1}, new double[]{0, 0, 0});
        QuadtreeIndex idx1 = new QuadtreeIndex(treeSpec, coord[0]);
        QuadtreeIndex idx2 = new QuadtreeIndex(treeSpec, coord[1]);
        idx1.expendIndex(20);
        idx2.expendIndex(20);
        Assert.assertEquals(idx1.compareTo(idx2), -1);
    }

    @Test
    public void randomSimpleThreeDimTest() {
        double[][] data = new double[5000][];
        Random r = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = new double[]{r.nextDouble(), r.nextDouble(), r.nextDouble()};
        }

        TreeMap<QuadtreeIndex, ThreeDim> map = new TreeMap<>();
        TreeSpec treeSpec = new TreeSpec(new double[]{1, 1, 1}, new double[]{0, 0, 0}, 20, 5);
        Function<ThreeDim, QuadtreeIndex> function = IndexUtils.quadTreeIndex(treeSpec, d -> d.getCoordinates());
        Set<ThreeDim> objects = new HashSet<>();
        for (int i = 0; i < data.length; i++) {
            ThreeDim d = new ThreeDim(data[i]);
            map.put(function.apply(d), d);
            objects.add(d);
        }
        int i = 0;
        //System.out.println(tree);
        for (ThreeDim d : objects) {
            Assert.assertTrue(map.containsValue(d), " object " + i + " not found " + d);
            i++;
        }

    }

}
