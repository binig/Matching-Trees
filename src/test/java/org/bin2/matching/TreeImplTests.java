package org.bin2.matching;

import org.bin2.matching.tree.TreeImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by benoitroger on 19/01/15.
 */
public class TreeImplTests {

    @Test
    public void testSimpleInsertGet() {
        TreeImpl<Integer,Integer> tree = new TreeImpl<>();
        Assert.assertNull(tree.get(1));
        tree.put(1, 1);
        Assert.assertEquals(tree.get(1),new Integer(1));
        tree.put(2, 2);
        Assert.assertEquals(tree.get(1),new Integer(1));
        Assert.assertEquals(tree.get(2),new Integer(2));

        tree.put(10, 20);
        tree.put(5, 10);
        tree.put(2, 4);
        tree.put(3, 6);
        tree.put(100, 200);
        tree.put(1, 2);
        Assert.assertEquals(tree.get(10),new Integer(20));
        Assert.assertEquals(tree.get(5),new Integer(10));
        Assert.assertEquals(tree.get(2),new Integer(4));
        Assert.assertEquals(tree.get(3),new Integer(6));
        Assert.assertEquals(tree.get(100),new Integer(200));
        Assert.assertEquals(tree.get(1),new Integer(2));
        Assert.assertNull(tree.get(80));

    }
}
