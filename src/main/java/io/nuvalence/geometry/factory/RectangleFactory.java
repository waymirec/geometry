package io.nuvalence.geometry.factory;

import io.nuvalence.geometry.dto.RectangleDTO;
import io.nuvalence.geometry.model.Rectangle;

final public class RectangleFactory {
    private RectangleFactory() {}

    public static Rectangle create(final RectangleDTO points)
    {
        return new Rectangle(
                points.getLowerLeftX(), points.getLowerLeftY(),
                points.getUpperRightX(), points.getUpperRightY());
    }
}
