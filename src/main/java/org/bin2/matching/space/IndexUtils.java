package org.bin2.matching.space;

import com.google.common.base.Preconditions;

import java.util.Arrays;

/**
 * Created by benoitroger on 05/02/15.
 */
public class IndexUtils {

    public static  <T>  int  compare(Index idx1, Index idx2 , boolean autoExpendIndex, int maxOrder, boolean indexOnly) {
        Preconditions.checkNotNull(idx1);
        Preconditions.checkNotNull(idx2);
        if(idx1==idx2) return 0;
        //TODO it may be worth to not check equality before since we do not expect much equality ?
        int innerValueComp = idx1.innerValuesCompare(idx2);
        if(innerValueComp==0) return 0;

        int idx=0;
        int currOrder = Math.min(idx1.getOrder(),idx2.getOrder());
        do  {
            while (idx < idx1.getIndex().length && idx < idx2.getIndex().length) {
                int comp = Byte.compare(idx1.getIndex()[idx],idx2.getIndex()[idx]);
                if (comp!=0) return comp;
                idx++;
            }
            if (autoExpendIndex) {
                currOrder += 4;
                idx1.expendIndex(currOrder);
                idx2.expendIndex(currOrder);
            }
        } while(autoExpendIndex&&currOrder<maxOrder);
        if (indexOnly) {
            return 0;
        }   else {
            return innerValueComp;
        }
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
