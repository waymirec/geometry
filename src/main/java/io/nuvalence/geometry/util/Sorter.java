package io.nuvalence.geometry.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Sorter<T> {
    private T a;
    private T b;
    private List<T> sorted;

    public Sorter()
    {
        this.a = null;
        this.b = null;
    }
    public Sorter(final T a, final T b)
    {
        this.a = a;
        this.b = b;
    }

    public Sorter(final T a, final T b, Comparator<T> comparator)
    {
        this.a = a;
        this.b = b;
        sort(comparator);
    }

    public void sort(final Comparator<T> comparator)
    {
        List<T> list = Arrays.asList(a, b);
        this.sorted = list.stream().sorted(comparator).collect(Collectors.toList());
    }

    public void sort(final T a, final T b, final Comparator<T> comparator)
    {
        this.a = a;
        this.b = b;
        sort(comparator);
    }

    public T first()
    {
        return sorted.get(0);
    }

    public T second()
    {
        return sorted.get(1);
    }
}
