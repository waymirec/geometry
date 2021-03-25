package io.nuvalence.geometry.service;

public enum RectangleAdjacencyState implements RectangleSubState {
    NOT_ADJACENT("not-adjacent"),
    PARTIAL("partial"),
    PROPER("proper"),
    SUB_LINE("sub-line")
    ;

    private final String value;

    RectangleAdjacencyState(String value)
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
