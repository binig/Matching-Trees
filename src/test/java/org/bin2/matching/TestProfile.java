package org.bin2.matching;

import java.util.Random;

/**
 * Created by benoitroger on 28/03/14.
 */
public class TestProfile {
    private static Random random = new Random();
    private long id;
    private long match;
    private long sort;

    public TestProfile(long id,long  match, long sort) {
        this.id=id;
        this.match = match;
        this.sort = sort;
    }

    public long getId() {
        return id;
    }

    public long getMatch() {
        return match;
    }

    public long getSort() {
        return sort;
    }

    public static TestProfile random(int id) {
        return  new TestProfile(id, random.nextInt(4000000), random.nextInt(4000000));
    }
}
