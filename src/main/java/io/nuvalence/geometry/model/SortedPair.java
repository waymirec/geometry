package io.nuvalence.geometry.model;

import java.util.Comparator;

public interface SortedPair<T extends Shape> {
    void set(final T a, final T b);
    void sort(final Comparator<T> comparator);
    T first();
    T first(final Comparator<T> comparator);
    T second();
    T second(final Comparator<T> comparator);


}
