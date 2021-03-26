package io.nuvalence.geometry.util;

import io.nuvalence.geometry.model.Point;
import io.nuvalence.geometry.model.Rectangle;
import io.nuvalence.geometry.model.RectanglePair;
import io.nuvalence.geometry.model.SortedPair;

public class RectangleExt {
    private RectangleExt() {}

    public static boolean isAbove(final Rectangle r1, final Rectangle r2)
    {
        if (r1 == null || r2 == null) return false;
        return PointExt.isAbove(r1.getUpperLeft(), r2.getUpperLeft());
    }

    public static boolean isBelow(final Rectangle r1, final Rectangle r2)
    {
        if (r1 == null || r2 == null) return false;
        return PointExt.isBelow(r1.getLowerLeft(), r2.getLowerLeft());
    }

    public static boolean isLeftOf(final Rectangle r1, final Rectangle r2)
    {
        if (r1 == null || r2 == null) return false;
        return PointExt.isLeftOf(r1.getLowerLeft(), r2.getLowerLeft());
    }

    public static boolean isRightOf(final Rectangle r1, final Rectangle r2)
    {
        if (r1 == null || r2 == null) return false;
        return PointExt.isRightOf(r1.getLowerRight(), r2.getLowerRight());
    }

    public static boolean overlaps(final Rectangle r1, final Rectangle r2)
    {
        if (r1 == null || r2 == null) return false;

        final SortedPair<Rectangle> rectangles = new RectanglePair(r1, r2, Comparators.RECTANGLE_VERTICAL);
        final Rectangle right = rectangles.first();
        final Rectangle left = rectangles.second();

        boolean hOverlap = PointExt.isLeftOf(right.getLowerLeft(), left.getLowerRight())
             && PointExt.isLeftOf(left.getLowerLeft(), right.getLowerRight());

        boolean vOverlap = PointExt.isBelow(right.getLowerLeft(), left.getUpperLeft())
             && PointExt.isBelow(left.getLowerLeft(), right.getUpperLeft());

        return hOverlap && vOverlap;
    }

    public static Rectangle clone(final Rectangle rectangle) {
        final Point p1 = PointExt.clone(rectangle.getLowerLeft());
        final Point p2 = PointExt.clone(rectangle.getUpperRight());
        return new Rectangle(p1, p2);
    }
}
