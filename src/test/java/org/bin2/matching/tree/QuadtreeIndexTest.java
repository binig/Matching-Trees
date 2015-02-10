package org.bin2.matching.tree;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;
import java.util.function.Function;

/**
 * Created by benoi_000 on 2/6/2015.
 */
public class QuadtreeIndexTest {

    @Test
    public void testDiffOrders() {
        double[] coord = new double[]{1, 2, 3};
        IndexConfiguration indexConfiguration = new IndexConfiguration(new double[]{10, 10, 10}, new double[]{0, 0, 0});
        QuadtreeIndex idx1 = new QuadtreeIndex(indexConfiguration, coord);
        QuadtreeIndex idx2 = new QuadtreeIndex(indexConfiguration, coord);
        idx1.expendIndex(4);
        idx1.expendIndex(128);
        idx2.expendIndex(4);
        Assert.assertEquals(idx1.compareTo(idx2), 0);
    }


    @Test
    public void testDiffOrders2() {
        double[][] coord = new double[][]{{0.4054175497278485, 0.28507660055555484, 0.2336113690806645}
                , {0.40066973024531627, 0.2927455088724463, 0.23923691050410156}};
        IndexConfiguration indexConfiguration = new IndexConfiguration(new double[]{1, 1, 1}, new double[]{0, 0, 0});
        QuadtreeIndex idx1 = new QuadtreeIndex(indexConfiguration, coord[0]);
        QuadtreeIndex idx2 = new QuadtreeIndex(indexConfiguration, coord[1]);
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
        IndexConfiguration indexConfiguration = new IndexConfiguration(new double[]{1, 1, 1}, new double[]{0, 0, 0}, 20, 5);
        Function<ThreeDim, QuadtreeIndex> function = QuadtreeBuilder.quadTreeIndex(indexConfiguration, d -> d.getCoordinates());
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

    @Test
    public void randomSimpleThreeDimTestWithBuilder() {
        double[][] data = new double[500][];
        Random r = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = new double[]{r.nextDouble(), r.nextDouble(), r.nextDouble()};
        }

        IndexConfiguration indexConfiguration = new IndexConfiguration(new double[]{1, 1, 1}, new double[]{0, 0, 0}, 20, 5);
        RTree<QuadtreeIndex,ThreeDim> rTree = RTree.<ThreeDim>quadtreeBuilder().withConfiguration(indexConfiguration)
                .withCoordinateTransform(d -> d.getCoordinates()).build();
        Set<ThreeDim> objects = new HashSet<>();
        for (int i = 0; i < data.length; i++) {
            ThreeDim d = new ThreeDim(data[i]);
            rTree.add(d);
            objects.add(d);
        }
        int i = 0;
        //System.out.println(tree);
        for (ThreeDim d : objects) {
            Assert.assertTrue(rTree.contains(d), " object " + i + " not found " + d);
            i++;
        }

    }

    //@Test
    public  void test2DcoordinateFind50Closest() {
        double[][] data = new double[1000][];
        Random r = new Random();

        for (int i = 0; i < data.length; i++) {
            data[i] = new double[]{r.nextDouble(), r.nextDouble()};
        }
        double[] x = new double[]{r.nextDouble(), r.nextDouble()};
        IndexConfiguration indexConfiguration = new IndexConfiguration(new double[]{1, 1}, new double[]{0, 0}, 20, 5);
        TreeSet<double[]> treeSet = new TreeSet<>(new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                double dist1 = (x[0]-o1[0])*(x[0]-o1[0])+(x[1]-o1[1])*(x[1]-o1[1]);
                double dist2 = (x[0]-o2[0])*(x[0]-o2[0])+(x[1]-o2[1])*(x[1]-o2[1]);
                int comp = Double.compare(dist1,dist2);
                if (comp==0) {
                    comp = Double.compare(o1[0],o2[0]);
                }
                if (comp==0) {
                    comp = Double.compare(o1[1],o2[1]);
                }
                return comp;
            }
        });
        RTree<QuadtreeIndex, double[] > rtree = RTree.<double[]>quadtreeBuilder()
                .withConfiguration(indexConfiguration).withCoordinateTransform(d-> d).build();
        for(double[] c:data) {
            rtree.add(c);
            treeSet.add(c);
        }
        Set<double[]> closeSet = new HashSet<>();
        for(int i=0;i<50;i++){
            closeSet.add(treeSet.pollFirst());
        }
        Assert.assertEquals(rtree.getValueArround(x,50),closeSet);

    }

}
