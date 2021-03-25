package io.nuvalence.geometry.service;

public enum RectangleRelationship implements RectangleState {
    ADJACENT("adjacent"),
    CONTAINED("contained"),
    INTERSECTED("intersected"),
    SEPARATED("separated")
    ;

    private final String value;

    RectangleRelationship(final String value)
    {
        this.value = value;
    }

    public String value()
    {
        return value;
    }

    public String toString()
    {
        return value;
    }
}
