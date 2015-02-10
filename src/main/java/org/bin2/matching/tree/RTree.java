package org.bin2.matching.tree;

import com.google.common.base.Preconditions;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

/**
 * Created by benoitroger on 10/02/15.
 */
public class RTree<I extends Index<I>,V> {

    private Map<I,V> map;
    private Function<V,I> toIndexFunction;

    public RTree(Function<V, I> toIndexFunction) {
        this.toIndexFunction = toIndexFunction;
        this.map = new TreeMap<>();
    }

    public V add(V value) {
        return map.put(toIndexFunction.apply(value),value);
    }

    /**
     * look for the value using the index O(log(n))
     * @param value
     * @return
     */
    public boolean contains(V value) {
        Preconditions.checkNotNull(value);
        I idx = toIndexFunction.apply(value);
        V v = map.get(idx);
        return v!=null&&v.equals(value);
    }


    public static <V> QuadtreeBuilder<V> quadtreeBuilder() {
        return QuadtreeBuilder.newBuilder();
    }
}
