package org.bin2.matching.space;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Range;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by benoitroger on 14/07/14.
 */
public class SpaceTest {

    @Test
    public void testBuildIndex() {
        SpaceDefinition sd = new SpaceDefinition(ImmutableList.of(
                new ContinuousAxisDefinition(10,0),
                new ContinuousAxisDefinition(10,0),
                new ContinuousAxisDefinition(10,0),
        new ContinuousAxisDefinition(10,0),
                new ContinuousAxisDefinition(10,0),
                new ContinuousAxisDefinition(10,0)
                ));
        double[][] coords = new double[][]{{9,6,7,9,6,7},{0,0,0,9,6,7},{10,10,10,9,6,7},{5,10,3,9,6,7},{1.2344d,4.3584543d,7,9,6,7}};
        double maxGap = 0;
        for (double[] c:coords) {
            long index = sd.buildIndex(c);
            Range<Double>[] ranges = sd.fromIndex(index);
            for (int i = 0; i < c.length; i++) {
                double gap = ranges[i].upperEndpoint() -ranges[i].lowerEndpoint();
                maxGap = Math.max(gap,maxGap);
                Assert.assertTrue(ranges[i].contains(c[i]),ranges[i].toString() + "  " + c[i]);
            }
        }
        //System.out.println(maxGap);
        //Assert.assertFalse(true);
    }
}
