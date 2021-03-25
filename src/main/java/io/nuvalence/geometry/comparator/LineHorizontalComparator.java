package io.nuvalence.geometry.comparator;

import io.nuvalence.geometry.model.Line;

import java.util.Comparator;

public class LineHorizontalComparator implements Comparator<Line> {
    @Override
    public int compare(Line l1, Line l2) {
        if (l1 == null && l2 == null) return 0;
        if (l1 == null) return -1;
        if (l2 == null) return 1;
        return Float.compare(l1.getFirstPoint().getX(), l2.getFirstPoint().getX());
    }
}
