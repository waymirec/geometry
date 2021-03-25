package io.nuvalence.geometry.comparator;

import io.nuvalence.geometry.model.Rectangle;

import java.util.Comparator;

public class RectangleHorizontalComparator implements Comparator<Rectangle> {
    @Override
    public int compare(Rectangle r1, Rectangle r2) {
        if (r1 == null && r2 == null) return 0;
        if (r1 == null) return -1;
        if (r2 == null) return 1;
        return Float.compare(r1.getUpperLeft().getX(), r2.getUpperLeft().getX());
    }
}
