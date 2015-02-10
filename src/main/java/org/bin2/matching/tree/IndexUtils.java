package org.bin2.matching.tree;

import com.google.common.base.Preconditions;

import java.util.function.Function;

/**
 * Created by benoitroger on 05/02/15.
 */
public class IndexUtils {

    public static <T> int compare(ComparableIndex idx1, ComparableIndex idx2, boolean autoExpendIndex, int maxOrder, int orderInc, boolean indexOnly) {
        Preconditions.checkNotNull(idx1);
        Preconditions.checkNotNull(idx2);
        if(idx1==idx2) return 0;
        //TODO it may be worth to not check equality before since we do not expect much equality ?
        int innerValueComp = idx1.innerValuesCompare(idx2);
        if(innerValueComp==0) return 0;

        int idx=0;
        int currOrder = Math.min(idx1.getOrder(),idx2.getOrder());
        if (currOrder == 0) {
            currOrder += orderInc;
            expendOrder(idx1, idx2, currOrder);
        }
        int[] idx1Byte = idx1.getIndex();
        int[] idx2Byte = idx2.getIndex();
        int numberOfSignificantBits = idx1.getNumberOfSignificantBits(currOrder);
        int startNOSB = numberOfSignificantBits;
        do  {
            while (numberOfSignificantBits > 0) {
                // check for uncomplete byte when one idx has a != order
                // should use a mask to clear the useless part of the bigger order comp
                int index1 = idx1Byte[idx];
                int index2 = idx2Byte[idx];
                if (numberOfSignificantBits < Integer.SIZE) {
                    int mask = ~((1 << (Integer.SIZE - numberOfSignificantBits)) - 1);
                    index1 &= mask;
                    index2 &= mask;
                }
                int comp = Integer.compareUnsigned(index1, index2);
                if (comp!=0) return comp;
                numberOfSignificantBits -= Integer.SIZE;
                idx++;
            }
            if (autoExpendIndex) {
                currOrder += orderInc;
                expendOrder(idx1, idx2, currOrder);
                // we need to reprocess the previous step
                //todo check if there is no overlap  ? for example 6 dimention and order 4
                // we get the expended indexes
                idx1Byte = idx1.getIndex();
                idx2Byte = idx2.getIndex();
                // rewind to previews state with an updated numberOfSignificantBits
                idx--;
                int newNOSB = idx1.getNumberOfSignificantBits(currOrder);
                numberOfSignificantBits += Integer.SIZE + newNOSB - startNOSB;
                startNOSB = newNOSB;


            }
        } while(autoExpendIndex&&currOrder<maxOrder);
        if (indexOnly) {
            return 0;
        }   else {
            return innerValueComp;
        }
    }

    private static void expendOrder(ComparableIndex idx1, ComparableIndex idx2, int currOrder) {
        idx1.expendIndex(currOrder);
        idx2.expendIndex(currOrder);
    }


    public static int innerValuesCompare(double[] coordinates1, double[] coordinates2 ) {
        Preconditions.checkArgument(coordinates1.length==coordinates2.length);
        for(int i=0;i<coordinates1.length;i++) {
            int comp = Double.compare(coordinates1[i],coordinates2[i]);
            if (comp!=0) return comp;
        }
        return 0;
    }
    
}
