import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by benoitroger on 17/10/14.
 */
public class Solution {

    public int maxProduct(int[] A) {
        Integer currentMax=null;
        Integer currentMin=null;
        Integer max = null;
        for (int i=0;i<A.length;i++) {
            int c = A[i];
            if (c==0) {
                currentMax = null;
                currentMin=null;


            } else {
                if (currentMax==null) {
                    currentMax = c;
                } else {
                    currentMax *=c;
                }
                if (currentMin!=null) {
                    currentMin *= c;
                    if (currentMin>currentMax) {
                        currentMax= currentMin;
                        currentMin=null;
                    }
                } else if (currentMax<0 ) {
                    currentMin=currentMax;
                    currentMax = null;
                }

                if (currentMax==null) {
                } else if (max==null ) {
                    max =currentMax;
                } else if (currentMax>max) {
                    max = currentMax;
                }

            }

        }
        return max;
    }


    @Test
    public void test() {
        Assert.assertEquals(maxProduct(new int[]{2,3 ,-2,4}), 6);
        Assert.assertEquals(maxProduct(new int[]{0,2,3 ,-2,4}), 6);
        Assert.assertEquals(maxProduct(new int[]{2,3 ,0,4}), 6);
        Assert.assertEquals(maxProduct(new int[]{2,3 ,-2,4,-1,-4}), 48);
        Assert.assertEquals(maxProduct(new int[]{2,3 ,-2,4,-1,-400}), 400);
    }
}
