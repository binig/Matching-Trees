package org.bin2.matching.context;

import java.util.Comparator;

/**
 * Created by benoitroger on 28/03/14.
 */
public class ContextEntryComparator implements Comparator<ContextEntry> {
    private final long mask;

    public ContextEntryComparator(long mask) {
        this.mask = mask;
    }

    @Override
    public int compare(ContextEntry o1, ContextEntry o2) {
        long comp = (o1.getMeSort()&mask) - (o2.getMeSort()&mask);
        if (comp ==0) {
            comp = o1.getId()-o2.getId();
        }
        return comp<0?-1:comp==0?0:1;
    }
}
