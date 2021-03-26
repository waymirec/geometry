package io.nuvalence.geometry.model;

public class Line implements Shape, Comparable<Line> {
    private final SortedPair<Line> lines = new LinePair();
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
}
