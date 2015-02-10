package org.bin2.matching.tree;

import com.google.common.base.Preconditions;

import java.util.*;
import java.util.function.Function;

/**
 * Created by benoitroger on 10/02/15.
 */
public class RTree<I extends Index<I>,V> {

    private SortedMap<I,V> map;
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

    public Collection<V> getValueArround(V value, int amount) {
        //TODO
        // we need to find the correct order and build the index range value
        // if too much take smaller order
        // take larger order
        return Collections.EMPTY_LIST;
    }

    public Collection<V> getValueBetween(V value1, V value2) {
        return map.subMap(toIndexFunction.apply(value1), toIndexFunction.apply(value2)).values();

    }


    public static <V> QuadtreeBuilder<V> quadtreeBuilder() {
        return QuadtreeBuilder.newBuilder();
    }
}
