package org.bin2.matching.tree;

/**
 * Created by benoi_000 on 2/5/2015.
 */
public interface CoordinateTransform<T> {

    double[] toCoordinate(T value);
}
