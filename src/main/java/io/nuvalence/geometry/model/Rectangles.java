package io.nuvalence.geometry.model;

import io.nuvalence.geometry.util.Comparators;
import io.nuvalence.geometry.util.Sorter;

public class Rectangles {
    private final Sorter<Rectangle> vsorter;
    private final Sorter<Rectangle> hsorter;

    public Rectangles(final Rectangle a, final Rectangle b) {
        this.vsorter = new Sorter<>(a,b, Comparators.RECTANGLE_VERTICAL);
        this.hsorter = new Sorter<>(a, b, Comparators.RECTANGLE_HORIZONTAL);
    }

    public Rectangle getLeft()
    {
        return hsorter.first();
    }

    public Rectangle getRight()
    {
        return hsorter.second();
    }

    public Rectangle getLower()
    {
        return vsorter.first();
    }

    public Rectangle getUpper()
    {
        return vsorter.second();
    }
}
