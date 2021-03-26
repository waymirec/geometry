package io.nuvalence.geometry.comparator;

import io.nuvalence.geometry.builder.LineBuilder;
import io.nuvalence.geometry.model.Line;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineComparatorTests {
    private final LineHorizontalComparator LINE_HCOMP = new LineHorizontalComparator();
    private final LineVerticalComparator LINE_VCOMP = new LineVerticalComparator();

    private List<Line> lines;

    @Test
    public void shouldSortLinesHorizontally()
    {
        givenLines(
                new LineBuilder()
                    .withFirstPoint(10f,1f)
                    .withSecondPoint(15f, 5f)
                    .build(),
                new LineBuilder()
                    .withFirstPoint(1f,1f)
                    .withSecondPoint(5f, 5f)
                    .build());

        whenSortedHorizontally();

        thenFirstLineIs(
                new LineBuilder()
                    .withFirstPoint(1f,1f)
                    .withSecondPoint(5f, 5f)
                    .build());
    }

    @Test
    public void shouldSortLinesVertically()
    {
        givenLines(
                new LineBuilder()
                        .withFirstPoint(1f,10f)
                        .withSecondPoint(10f, 10f)
                        .build(),
                new LineBuilder()
                        .withFirstPoint(1f,1f)
                        .withSecondPoint(10f, 5f)
                        .build());

        whenSortedVertically();

        thenFirstLineIs(
                new LineBuilder()
                        .withFirstPoint(1f,1f)
                        .withSecondPoint(10f, 5f)
                        .build());
    }

    private void givenLines(Line l1, Line l2)
    {
        this.lines = Arrays.asList(l1, l2);
    }

    private void whenSortedHorizontally()
    {
        lines.sort(LINE_HCOMP);
    }

    private void whenSortedVertically()
    {
        lines.sort(LINE_VCOMP);
    }

    private void thenFirstLineIs(Line expected)
    {
        assertEquals(expected, lines.get(0));
    }
}
