package io.nuvalence.geometry.model;

import io.nuvalence.geometry.util.Comparators;
import io.nuvalence.geometry.util.Sorter;

public class Line implements Comparable<Line> {
    private final Sorter<Line> sorter = new Sorter<>();
    private final Point firstPoint;
    private final Point secondPoint;
    private final boolean horizontal;

    public Line(final Point firstPoint, final Point secondPoint)
    {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
        this.horizontal = firstPoint.getY() == secondPoint.getY();
    }

    public float length()
    {
        return horizontal
                ? secondPoint.getX() - firstPoint.getX()
                : secondPoint.getY() - firstPoint.getY();
    }

    public Point getFirstPoint()
    {
        return firstPoint;
    }

    public Point getSecondPoint()
    {
        return secondPoint;
    }

    public boolean isHorizontal()
    {
        return horizontal;
    }

    public boolean isVertical()
    {
        return !horizontal;
    }

    public boolean isLeftOf(final Line other)
    {
        return other == null || this.getFirstPoint().getX() < other.getFirstPoint().getX();
    }

    public boolean isRightOf(final Line other)
    {
        return other == null || this.getFirstPoint().getX() > other.getFirstPoint().getX();
    }

    public boolean isAbove(final Line other)
    {
        return other == null || this.getFirstPoint().getY() > other.getSecondPoint().getY();
    }

    public boolean isBelow(final Line other)
    {
        return other == null || this.getSecondPoint().getY() < other.getFirstPoint().getY();
    }

    public boolean isParallelTo(final Line other)
    {
        return other != null && this.horizontal == other.horizontal;
    }

    public boolean isPerpendicularTo(final Line other)
    {
        return other != null && !isParallelTo(other);
    }

    public boolean isAdjacentTo(final Line other)
    {
        if (isPerpendicularTo(other)) return false;
        if (horizontal)
        {
            sorter.sort(this, other, Comparators.LINE_VERTICAL);
            Line left = sorter.first();
            Line right = sorter.second();
            return (left.firstPoint.getY() == right.firstPoint.getY()) &&
                   (left.secondPoint.getX() > right.firstPoint.getX());
        }
        else
        {
            sorter.sort(this, other, Comparators.LINE_HORIZONTAL);
            Line lower = sorter.first();
            Line upper = sorter.second();
            return (lower.firstPoint.getX() == upper.firstPoint.getX()) &&
                   (lower.secondPoint.getY() > upper.firstPoint.getY());
        }
    }

    public boolean intersectsWith(final Line other)
    {
        if (other == null) return false;
        if (isParallelTo(other)) return false;
        sorter.sort(this, other, Comparators.LINE_VERTICAL);

        final Line lower = sorter.first();
        final Line upper = sorter.second();
        return (lower.secondPoint.getY() > upper.secondPoint.getY())
               && (upper.secondPoint.getX() > lower.firstPoint.getX()
               && (upper.firstPoint.getX() < lower.firstPoint.getX()));
    }

    public boolean contains(Point point)
    {
        if (point == null) return false;
        return point.getX() >= firstPoint.getX() && point.getX() <= secondPoint.getX() && point.getY() >= firstPoint.getY() && point.getY() <= secondPoint.getY();
    }

    public Point findIntersection(Line other)
    {
        float x1 = firstPoint.getX();
        float y1 = firstPoint.getY();
        float x2 = secondPoint.getX();
        float y2 = secondPoint.getY();

        float x3 = other.firstPoint.getX();
        float y3 = other.firstPoint.getY();
        float x4 = other.secondPoint.getX();
        float y4 = other.secondPoint.getY();

        float denominator = (x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4);
        if (denominator == 0)
            return null;

        float xNominator = (x1*y2 - y1*x2)*(x3 - x4) - (x1 - x2)*(x3*y4 - y3*x4);
        float yNominator = (x1*y2 - y1*x2)*(y3 - y4) - (y1 - y2)*(x3*y4 - y3*x4);

        float px = xNominator / denominator;
        float py = yNominator / denominator;

        Point p = new Point(px,py);
        return this.contains(p) && other.contains(p) ? p : null;
    }

    @Override
    public String toString()
    {
        return String.format("LineSegment(p1: %s, p2: %s)", firstPoint, secondPoint);
    }

    @Override
    public int compareTo(Line o)
    {
        if (o == null) return 1;
        return secondPoint.compareTo(o.secondPoint);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + firstPoint.hashCode();
        hash = 31 * hash + secondPoint.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Line))
            return false;

        Line other = (Line)o;
        return firstPoint.equals(other.firstPoint) && secondPoint.equals(other.secondPoint);
    }

    @Override
    protected Line clone() {
        return new Line(firstPoint.clone(), secondPoint.clone());
    }
}
