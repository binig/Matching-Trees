package org.bin2.matching.tree;

import java.util.Arrays;

/**
 * Created by benoitroger on 06/02/15.
 */
public class ThreeDim {
    double[] coordinates;

    public ThreeDim(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return Arrays.toString(coordinates);
    }
}
