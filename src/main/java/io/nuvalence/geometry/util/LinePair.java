package io.nuvalence.geometry.util;

import io.nuvalence.geometry.model.Line;

public class LinePair extends AbstractSortedPair<Line> {
    public LinePair()
    {
        super(Comparators.LINE_HORIZONTAL, Line.class);
    }
}
