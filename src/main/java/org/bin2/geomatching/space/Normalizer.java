package org.bin2.geomatching.space;

import java.math.BigDecimal;

/**
 * Created by benoitroger on 17/10/14.
 * every axis should have their value normalize according to their weights and amplitudes
 */
public interface Normalizer {
    /**
     *
     * @param value to normalize
     * @return a normalized value between 0-1
     */
    double normalize(double value);

    /**
     * this is the reverse operation of normalization
     * @param value to unormalize
     * @return the unormalize value
     */
    double unormalize(double value);
}

