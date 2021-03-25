package io.nuvalence.geometry.util;

import io.nuvalence.geometry.comparator.LineHorizontalComparator;
import io.nuvalence.geometry.comparator.LineLengthComparator;
import io.nuvalence.geometry.comparator.RectangleHorizontalComparator;
import io.nuvalence.geometry.comparator.LineVerticalComparator;
import io.nuvalence.geometry.comparator.RectangleVerticalComparator;
import io.nuvalence.geometry.model.Line;
import io.nuvalence.geometry.model.Rectangle;

import java.util.Comparator;

final public class Comparators {
    private Comparators() {}

    public static final Comparator<Rectangle> RECTANGLE_HORIZONTAL = new RectangleHorizontalComparator();
    public static final Comparator<Rectangle> RECTANGLE_VERTICAL = new RectangleVerticalComparator();
    public static final Comparator<Line> LINE_HORIZONTAL = new LineHorizontalComparator();
    public static final Comparator<Line> LINE_VERTICAL = new LineVerticalComparator();
    public static final Comparator<Line> LINE_LENGTH = new LineLengthComparator();
}
