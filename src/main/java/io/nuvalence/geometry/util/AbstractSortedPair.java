package io.nuvalence.geometry.util;

import io.nuvalence.geometry.model.Shape;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class AbstractSortedPair<T extends Shape> implements SortedPair<T> {
    private final Comparator<T> comparator;
    private final T[] items;

    @SuppressWarnings("unchecked")
    public AbstractSortedPair(Comparator<T> comparator, Class<T> clazz)
    {
        this.comparator = comparator;
        this.items = (T[])Array.newInstance(clazz, 2);
    }

    public void set(final T a, final T b)
    {
        items[0] = a;
        items[1] = b;
        Arrays.sort(items, comparator);
    }

    public void sort(final Comparator<T> comparator)
    {
        Arrays.sort(items, comparator);
    }

    public T first()
    {
        return items[0];
    }

    public T first(final Comparator<T> comparator)
    {
        final T[] temp = Arrays.copyOfRange(items, 0, 1);
        Arrays.sort(temp, comparator);
        return temp[0];
    }

    public T second()
    {
        return items[1];
    }

    public T second(final Comparator<T> comparator)
    {
        final T[] temp = Arrays.copyOfRange(items, 1, 1);
        Arrays.sort(temp, comparator);
        return temp[0];
    }
}
