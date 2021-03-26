package io.nuvalence.geometry.util;

import io.nuvalence.geometry.model.Line;
import io.nuvalence.geometry.model.LinePair;
import io.nuvalence.geometry.model.Point;
import io.nuvalence.geometry.model.SortedPair;

public class LineExt {
    private LineExt() { }

    public static boolean isLeftOf(final Line l1, final Line l2)
    {
        if(l1 == null || l2 == null) return false;
        return l1.getFirstPoint().getX() < l2.getFirstPoint().getX();
    }

    public static boolean isRightOf(final Line l1, final Line l2)
    {
        if(l1 == null || l2 == null) return false;
        return l1.getFirstPoint().getX() > l2.getFirstPoint().getX();
    }

    public static boolean isAbove(final Line l1, final Line l2)
    {
        if(l1 == null || l2 == null) return false;
        return l1.getFirstPoint().getY() > l2.getSecondPoint().getY();
    }

    public static boolean isBelow(final Line l1, final Line l2)
    {
        if(l1 == null || l2 == null) return false;
        return l1.getSecondPoint().getY() < l2.getFirstPoint().getY();
    }

    public static boolean isParallelTo(final Line l1, final Line l2)
    {
        if(l1 == null || l2 == null) return false;
        return l1.isHorizontal() == l2.isHorizontal();
    }

    public static boolean isPerpendicularTo(final Line l1, final Line l2)
    {
        if(l1 == null || l2 == null) return false;
        return !isParallelTo(l1, l2);
    }

    public static boolean isAdjacentTo(final Line l1, final Line l2)
    {
        if(l1 == null || l2 == null) return false;
        if (isPerpendicularTo(l1, l2)) return false;
        final SortedPair<Line> lines = new LinePair(l1, l2);
        if (l1.isHorizontal())
        {
            lines.sort(Comparators.LINE_HORIZONTAL);
            Line left = lines.first();
            Line right = lines.second();
            return (left.getFirstPoint().getY() == right.getFirstPoint().getY()) &&
                   (left.getSecondPoint().getX() > right.getFirstPoint().getX());
        }
        else
        {
            lines.sort(Comparators.LINE_VERTICAL);
            Line lower = lines.first();
            Line upper = lines.second();
            return (lower.getFirstPoint().getX() == upper.getFirstPoint().getX()) &&
                   (lower.getSecondPoint().getY() > upper.getFirstPoint().getY());
        }
    }

    public static boolean intersectsWith(final Line l1, final Line l2)
    {
        if(l1 == null || l2 == null) return false;
        if (isParallelTo(l1, l2)) return false;

        final SortedPair<Line> lines = new LinePair(l1, l2, Comparators.LINE_VERTICAL);
        final Line lower = lines.first();
        final Line upper = lines.second();
        return (lower.getSecondPoint().getY() > upper.getSecondPoint().getY())
            && (upper.getSecondPoint().getX() > lower.getFirstPoint().getX()
            && (upper.getFirstPoint().getX() < lower.getFirstPoint().getX()));
    }

    public static Point findIntersection(final Line l1, final Line l2)
    {
        float x1 = l1.getFirstPoint().getX();
        float y1 = l1.getFirstPoint().getY();
        float x2 = l1.getSecondPoint().getX();
        float y2 = l1.getSecondPoint().getY();

        float x3 = l2.getFirstPoint().getX();
        float y3 = l2.getFirstPoint().getY();
        float x4 = l2.getSecondPoint().getX();
        float y4 = l2.getSecondPoint().getY();

        float denominator = (x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4);
        if (denominator == 0)
            return null;

        float xNominator = (x1*y2 - y1*x2)*(x3 - x4) - (x1 - x2)*(x3*y4 - y3*x4);
        float yNominator = (x1*y2 - y1*x2)*(y3 - y4) - (y1 - y2)*(x3*y4 - y3*x4);

        float px = xNominator / denominator;
        float py = yNominator / denominator;

        Point p = new Point(px,py);
        return contains(l1, p) && contains(l2, p) ? p : null;
    }

    public static boolean contains(final Line line, Point point)
    {
        if (point == null) return false;
        return point.getX() >= line.getFirstPoint().getX()
            && point.getX() <= line.getSecondPoint().getX()
            && point.getY() >= line.getFirstPoint().getY()
            && point.getY() <= line.getSecondPoint().getY();
    }

    public static Line clone(final Line line) {
        final Point p1 = PointExt.clone(line.getFirstPoint());
        final Point p2 = PointExt.clone(line.getSecondPoint());
        return new Line(p1, p2);
    }
}
