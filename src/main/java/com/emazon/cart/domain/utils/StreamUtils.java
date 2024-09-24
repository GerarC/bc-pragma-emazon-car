package com.emazon.cart.domain.utils;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A collection of util functions that let me short a little bit the code,
 * avoiding all the verbosity of Stream Library
 */
public class StreamUtils {
    private StreamUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Zip two lists into the size of the first list
     * @param keys List which contains keys of the map
     * @param values List which contains values of the map
     * @return A map that represents the zipped lists
     * @param <K> Type of the key list
     * @param <V> Type of the value list
     */
    public static <K, V> Map<K, V> zip(List<K> keys, List<V> values) {
        Iterator<K> keyIterator = keys.iterator();
        Iterator<V> valueIterator = values.iterator();
        return IntStream.range(0, keys.size())
                .boxed().collect(Collectors.toMap(
                        key -> keyIterator.next(),
                        value -> valueIterator.next()
                ));

    }

    public static <A, B> List<B> map(List<A> items, Function<A, B> mapper) {
        return items.stream().map(mapper).toList();
    }

    public static <A> A findAny(List<A> items, Predicate<A> predicate, RuntimeException exception){
        return items.stream().filter(predicate).findAny().orElseThrow(() -> exception);
    }

    /**
     * Maps a list into another type of object and reduce that map into one value
     * @param items the initial list
     * @param mapper a mapping function
     * @param reducer the reducing function
     * @param initialValue initial value of reducing function
     * @return Calculated value
     * @param <A> Type of the initial list
     * @param <B> type of the result item
     */

    public static <A, B> B mapAndReduce(List<A> items, B initialValue, Function<A, B> mapper, BinaryOperator<B> reducer){
        return items.stream().map(mapper).reduce(initialValue, reducer);
    }
}
