package org.bin2.geomatching.context;

/**
 * Created by benoitroger on 28/03/14.
 */
public class ContextEntry {
    private final long id;
    private final long me;
    private final long meSort;

    private ContextEntry(Builder builder) {
        this.id = builder.id;
        this.me = builder.me;
        this.meSort = builder.meSort;
    }
    public long getId() {
        return id;
    }

    public long getMe() {
        return me;
    }


    public long getMeSort() {
        return meSort;
    }

    public static Builder newBuilder() {
        return new Builder();
    }
    public static class Builder {
        private long id;
        private long me;
        private long meSort;

        private Builder() {

        }
        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withMatch(long match) {
            this.me = match;
            return this;
        }
        public Builder withSort(long sort) {
            this.meSort = sort;
            return this;
        }

        public ContextEntry build() {
            return new ContextEntry(this);
        }
    }
}
