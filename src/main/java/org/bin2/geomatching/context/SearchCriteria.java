package org.bin2.geomatching.context;

/**
 * Created by benoitroger on 28/03/14.
 */
public class SearchCriteria {
    private final long match;
    private final long sortMask;

    public SearchCriteria(long match, long sortMask) {
        this.match = match;
        this.sortMask = sortMask;
    }

    public long getMatch() {
        return match;
    }

    public long getSortMask() {
        return sortMask;
    }
}
