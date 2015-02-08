package org.bin2.matching.tree;

import com.beust.jcommander.internal.Lists;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by benoitroger on 06/02/15.
 */
public class TreeImplTest {
    public static final int number = 10;

    @Test
    public void SimpleThreeDimTest() {
        double[][] data = new double[][]{
                {5, 5, 5},
                {1, 2, 3},
                {3, 2, 1},
                {4, 9, 8},
                {9, 8, 8},
                {0, 0, 0},
        };
        TreeImpl<QuadtreeIndex, ThreeDim> tree = TreeImpl.<QuadtreeIndex, ThreeDim>newQuadtreeBuilder()
                .max(new double[]{10, 10, 10}).min(new double[]{0, 0, 0})
                .coordinateTransform(d -> d.getCoordinates()).build();
        List<ThreeDim> objects = Lists.newArrayList();
        for (int i = 0; i < data.length; i++) {
            ThreeDim d = new ThreeDim(data[i]);
            tree.put(d);
            objects.add(d);
        }
        Assert.assertEquals(tree.size(), data.length);
        int i = 0;
        System.out.println(tree);
        for (ThreeDim d : objects) {
            Assert.assertTrue(tree.containsValue(d), " object " + i + " not found " + d);
            i++;
        }

    }

    @Test
    public void SimpleIndexTest() {
        TreeImpl<SimpleIndex, SimpleIndex> tree = TreeImpl.<SimpleIndex, SimpleIndex>newCustomIndexBuilder()
                .max(new double[]{10, 10, 10}).min(new double[]{0, 0, 0})
                .indexFunction(d -> d).build();
        int[] data = new int[]{2, 7, 0, 1, 3, 4, 6, 5, 9};
        List<SimpleIndex> objects = Lists.newArrayList();
        for (int i = 0; i < data.length; i++) {
            SimpleIndex d = new SimpleIndex(data[i]);
            tree.put(d);
            objects.add(d);
        }
        Assert.assertEquals(tree.size(), data.length);
        int i = 0;
        System.out.println(tree);
        for (SimpleIndex d : objects) {
            Assert.assertTrue(tree.contains(d), " object " + i + " not found " + d);
            i++;
        }

    }

}
