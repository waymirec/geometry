package io.nuvalence.geometry.model;

import io.nuvalence.geometry.util.Comparators;

import java.util.Comparator;

public class RectanglePair extends AbstractSortedPair<Rectangle> {
    public RectanglePair(final Rectangle r1, final Rectangle r2)
    {
        this(r1, r2, Comparators.RECTANGLE_HORIZONTAL);
        set(r1, r2);
    }

    public RectanglePair(final Rectangle r1, final Rectangle r2, final Comparator<Rectangle> comparator)
    {
        super(comparator, Rectangle.class);
        set(r1, r2);
    }
}
