package org.bin2.matching.tree;

import java.util.function.Function;

/**
 * Created by benoitroger on 10/02/15.
 */
public class QuadtreeBuilder<V> {
    private IndexConfiguration indexConfiguration;
    private CoordinateTransform<V> coordinateTransform;

    public static <T> Function<T, QuadtreeIndex> quadTreeIndex(IndexConfiguration indexConfiguration, CoordinateTransform<T> coordinateTransform) {
        return  new QuadtreeIndexFunction<>(indexConfiguration, coordinateTransform);

    }

    public static <V> QuadtreeBuilder<V> newBuilder() {
        return new QuadtreeBuilder();
    }


    private static class QuadtreeIndexFunction<T> implements Function<T, QuadtreeIndex> {
        private final CoordinateTransform<T> coordinateTransform;
        private final IndexConfiguration indexConfiguration;

        public QuadtreeIndexFunction(IndexConfiguration indexConfiguration, CoordinateTransform<T> coordinateTransform) {
            this.coordinateTransform = coordinateTransform;
            this.indexConfiguration = indexConfiguration;
        }

        @Override
        public QuadtreeIndex apply(T t) {
            double[] coords = coordinateTransform.toCoordinate(t);
            return new QuadtreeIndex(indexConfiguration, coords);
        }
    }

    public QuadtreeBuilder<V> withCoordinateTransform(CoordinateTransform<V> coordinateTransform) {
        this.coordinateTransform=coordinateTransform;
        return this;
    }
    public QuadtreeBuilder<V> withConfiguration(IndexConfiguration indexConfiguration) {
        this.indexConfiguration=indexConfiguration;
        return this;
    }



    public RTree<QuadtreeIndex, V> build() {
        return new RTree<>(quadTreeIndex(indexConfiguration,coordinateTransform) );
    }
}
