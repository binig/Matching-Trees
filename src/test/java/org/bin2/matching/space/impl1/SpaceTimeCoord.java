package org.bin2.matching.space.impl1;

/**
 * Created by benoitroger on 19/01/15.
 */
public class SpaceTimeCoord {
    private final long time;
    private final long x;
    private final long y;
    private final long z;

    public SpaceTimeCoord(long time, long x, long y, long z) {
        this.time = time;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public long getTime() {
        return time;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public long getZ() {
        return z;
    }
}
