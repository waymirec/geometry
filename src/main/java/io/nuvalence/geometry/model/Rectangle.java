package io.nuvalence.geometry.model;

final public class Rectangle implements Shape {
    private final Line leftLine;
    private final Line topLine;
    private final Line rightLine;
    private final Line bottomLine;

    public Rectangle(final float lowerLeftX, final float lowerLeftY, final float upperRightX, final float upperRightY)
    {
        this(new Point(lowerLeftX, lowerLeftY), new Point(upperRightX, upperRightY));
    }

    public Rectangle(final Point lowerLeft, final Point upperRight)
    {
        Point upperLeft = new Point(lowerLeft.getX(), upperRight.getY());
        Point lowerRight = new Point(upperRight.getX(), lowerLeft.getY());

        this.leftLine = new Line(lowerLeft, upperLeft);
        this.topLine = new Line(upperLeft, upperRight);
        this.rightLine = new Line(lowerRight, upperRight);
        this.bottomLine = new Line(lowerLeft, lowerRight);

        validate();
    }

    public Point getUpperLeft()
    {
        return topLine.getFirstPoint();
    }

    public Point getLowerLeft()
    {
        return bottomLine.getFirstPoint();
    }

    public Point getUpperRight()
    {
        return topLine.getSecondPoint();
    }

    public Point getLowerRight()
    {
        return bottomLine.getSecondPoint();
    }

    public Line getLeftLine()
    {
        return leftLine;
    }

    public Line getTopLine()
    {
        return topLine;
    }

    public Line getRightLine()
    {
        return rightLine;
    }

    public Line getBottomLine()
    {
        return bottomLine;
    }

    public float getHeight()
    {
        return getUpperLeft().getY() - getLowerLeft().getY();
    }

    public float getWidth()
    {
        return getUpperRight().getX() - getUpperLeft().getX();
    }

    public boolean isAbove(final Rectangle other)
    {
        return other == null || this.getUpperLeft().isAbove(other.getUpperLeft());
    }

    public boolean isBelow(final Rectangle other)
    {
        return other == null || this.getLowerLeft().isBelow(other.getLowerLeft());
    }

    public boolean isLeftOf(final Rectangle other)
    {
        return other == null || this.getLowerLeft().isLeftOf(other.getLowerLeft());
    }

    public boolean isRightOf(final Rectangle other)
    {
        return other == null || this.getLowerRight().isRightOf(other.getLowerRight());
    }

    public boolean overlaps(final Rectangle other)
    {
        if (other == null) return false;

        Rectangle left, right;
        if (this.getUpperLeft().getX() < other.getUpperLeft().getX())
        {
            left = this;
            right = other;
        }
        else
        {
            right = this;
            left = other;
        }

        boolean horizontalOverlap = right.getLowerLeft().isLeftOf(left.getLowerRight()) && left.getLowerLeft().isLeftOf(right.getLowerRight());
        boolean verticalOverlap = right.getLowerLeft().isBelow(left.getUpperLeft()) && left.getLowerLeft().isBelow(right.getUpperLeft());
        return horizontalOverlap && verticalOverlap;

    }

    @Override
    public String toString()
    {
        return String.format("Rectangle(lowerLeft: %s, upperRight: %s)", getLowerLeft(), getUpperRight());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + leftLine.hashCode();
        hash = 31 * hash + topLine.hashCode();
        hash = 31 * hash + rightLine.hashCode();
        hash = 31 * hash + bottomLine.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Rectangle))
            return false;

        Rectangle other = (Rectangle)o;
        return leftLine.equals(other.leftLine) &&
                topLine.equals(other.topLine) &&
                rightLine.equals(other.rightLine) &&
                bottomLine.equals(other.bottomLine);
    }

    @Override
    protected Rectangle clone() {
        return new Rectangle(this.getLowerLeft().clone(), this.getUpperRight().clone());
    }

    private void validate()
    {
        if((this.getLowerLeft().getX() >= this.getUpperRight().getX()) ||
           (this.getLowerLeft().getY() >= this.getUpperRight().getY()))
            throw new IllegalArgumentException("invalid bounds");
    }
}
