package org.bin2.matching.tree;

import org.testng.annotations.Test;

import java.util.Random;

/**
 * Created by benoitroger on 06/02/15.
 */
public class TreeImplTest {
    public static final int number = 10000;

    @Test
    public void SimpleThreeDimTest() {
        TreeImpl<QuadtreeIndex, ThreeDim> tree = TreeImpl.<QuadtreeIndex, ThreeDim>newBuilder()
                .max(new double[]{10, 10, 10}).min(new double[]{0, 0, 0})
                .coordinateTransform(d -> d.getCoordinates()).build();

        Random random = new Random();
        for (int i = 0; i < number; i++) {
            ThreeDim d = new ThreeDim(new double[]{random.nextDouble() * 10, random.nextDouble() * 10, random.nextDouble() * 10});
            tree.put(d);
        }
    }
}
