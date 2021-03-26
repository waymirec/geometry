package io.nuvalence.geometry.builder;

import io.nuvalence.geometry.model.Line;
import io.nuvalence.geometry.model.Point;

public class LineBuilder {
    private float x1, y1, x2, y2;

    public LineBuilder withFirstPoint(float x, float y)
    {
        this.x1 = x;
        this.y1 = y;
        return this;
    }

    public LineBuilder withSecondPoint(float x, float y)
    {
        this.x2 = x;
        this.y2 = y;
        return this;
    }

    public Line build()
    {
        final Point p1 = new Point(x1, y1);
        final Point p2 = new Point(x2, y2);
        return new Line(p1, p2);
    }
}
