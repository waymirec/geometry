package io.nuvalence.geometry.model;

final public class Point implements Shape, Comparable<Point> {
    private final float x;
    private final float y;

    public Point(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public boolean isRightOf(Point other)
    {
        return other == null || this.x > other.x;
    }

    public boolean isLeftOf(Point other)
    {
        return other == null || this.x < other.x;
    }

    public boolean isAbove(Point other)
    {
        return other == null || this.y > other.y;
    }

    public boolean isBelow(Point other)
    {
        return other == null || this.y < other.y;
    }

    @Override
    public String toString()
    {
        return String.format("(%.2f,%.2f)", x, y);
    }

    @Override
    public int compareTo(Point o)
    {
        if (o == null) return 1;
        return x != o.x ? Float.compare(x, o.x) : Float.compare(y, o.y);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int)x;
        hash = 31 * hash + (int)y;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Point))
            return false;

        Point other = (Point)o;
        return other.x == x && other.y == y;
    }

    @Override
    protected Point clone() {
        return new Point(this.x, this.y);
    }
}
