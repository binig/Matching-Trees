package org.bin2.matching.context;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by benoitroger on 28/03/14.
 */
public class MatchingContext<T> {
    private final ContextEntry[] entries;
    private final Function<T, ContextEntry> toContextEntry;
    private final Function<T, SearchCriteria> toSearchCriteria;

    private MatchingContext(Builder<T> builder) {
        this.toContextEntry = builder.toContextEntry;
        this.toSearchCriteria = builder.toSearchCriteria;
        this.entries = builder.entries.toArray(new ContextEntry[builder.entries.size()]);
    }

    public ContextEntry[] getMatch(T profile) {
        SearchCriteria c = toSearchCriteria.apply(profile);

        ContextEntry[] result  = new ContextEntry[entries.length];
        //SortedSet<ContextEntry> result = new TreeSet<ContextEntry>(new ContextEntryComparator(c.getSortMask()));
        int count = 0;
        for (ContextEntry e:entries) {
            if ( (e.getMe()&c.getMatch()) ==c.getMatch() || (e.getMeSort()&c.getMatch()) ==c.getMatch()) {
                result[count] = e;
                count ++;
            }
        }
        ContextEntry[] es =  new ContextEntry[count];
        System.arraycopy(result,0,es, 0, count);
        Arrays.sort(es, new ContextEntryComparator(c.getSortMask()));
        return es;
    }

    public  static <T> Builder<T> newBuilder() {
        return new Builder<T>();
    }
    public static  class Builder<T> {
        private ArrayList<ContextEntry> entries;
        private Function<T, ContextEntry> toContextEntry;
        private Function<T, SearchCriteria> toSearchCriteria;

        private Builder() {
             entries = new ArrayList<>();
        }

        public Builder<T> withContextEntryFunction(Function<T, ContextEntry> toContextEntry) {
            this.toContextEntry=toContextEntry;
            return this;
        }
        public Builder<T> withSearCriteriaFunction(Function<T, SearchCriteria> toSearchCriteria) {
            this.toSearchCriteria=toSearchCriteria;
            return this;
        }

        public Builder<T> addProfile(T profile) {
            Preconditions.checkNotNull(toContextEntry, "use withContextEntryFunction before adding profile");
            entries.add(toContextEntry.apply(profile));
            return this;
        }

        public MatchingContext<T> build() {
            return new MatchingContext<T>(this);
        }
    }
}
