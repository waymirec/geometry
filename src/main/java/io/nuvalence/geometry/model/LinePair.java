package io.nuvalence.geometry.model;

import io.nuvalence.geometry.util.Comparators;

import java.util.Comparator;

public class LinePair extends AbstractSortedPair<Line>
{
    public LinePair()
    {
        super(Comparators.LINE_HORIZONTAL, Line.class);
    }

    public LinePair(final Line l1, final Line l2)
    {
        this(l1, l2, Comparators.LINE_HORIZONTAL);
    }

    public LinePair(final Line l1, final Line l2, final Comparator<Line> comparator)
    {
        super(comparator, Line.class);
        set(l1, l2);
    }
}
