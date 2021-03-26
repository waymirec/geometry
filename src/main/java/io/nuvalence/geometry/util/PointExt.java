package io.nuvalence.geometry.util;

import io.nuvalence.geometry.model.Point;

public class PointExt {
    private PointExt() {}

    public static boolean isRightOf(final Point p1, final Point p2)
    {
        if (p1 == null || p2 == null) return false;
        return p1.getX() > p2.getX();
    }

    public static boolean isLeftOf(final Point p1, final Point p2)
    {
        if (p1 == null || p2 == null) return false;
        return p1.getX() < p2.getX();
    }

    public static boolean isAbove(final Point p1, final Point p2)
    {
        if (p1 == null || p2 == null) return false;
        return p1.getY() > p2.getY();
    }

    public static boolean isBelow(final Point p1, final Point p2)
    {
        if (p1 == null || p2 == null) return false;
        return p1.getY() < p2.getY();
    }

    public static Point clone(final Point point) {
        return new Point(point.getX(), point.getY());
    }
}
