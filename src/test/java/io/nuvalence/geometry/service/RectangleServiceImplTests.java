package io.nuvalence.geometry.service;

import io.nuvalence.geometry.dto.AnalyzeRectangleRequest;
import io.nuvalence.geometry.dto.AnalyzeRectangleResponse;
import io.nuvalence.geometry.dto.PointDTO;
import io.nuvalence.geometry.dto.RectangleDTO;
import io.nuvalence.geometry.factory.RectangleFactory;
import io.nuvalence.geometry.model.Rectangle;
import io.nuvalence.geometry.util.Comparators;
import io.nuvalence.geometry.util.RectanglePair;
import io.nuvalence.geometry.util.SortedPair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RectangleServiceImplTests {
    private static final Comparator<PointDTO> POINT_DTO_COMPARATOR = Comparator.comparing(PointDTO::getX).thenComparing(PointDTO::getY);

    private RectangleDTO firstRectangle;
    private RectangleDTO secondRectangle;
    private AnalyzeRectangleResponse response;

    @Autowired
    private RectangleService rectangleService;

    //region Tests
    @ParameterizedTest
    @MethodSource("provideIntersectingRectangles")
    public void givenIntersectingRectangles_whenAnalyzed_ShouldReturnExpectedValues(TestArgs args)
    {
        givenFirstRectangle(args.firstRectangle);
        givenSecondRectangle(args.secondRectangle);

        whenAnalyzed();

        thenResponseStateIs(RectangleRelationship.INTERSECTED);
        thenResponseSubstateIs(null);
        thenResponseIntersectionsIs(args.expectedIntersections);
    }

    @ParameterizedTest
    @MethodSource("provideSeparatedRectangles")
    public void givenSeparatedRectangles_WhenAnalyzed_ShouldReturnExpectedValues(TestArgs args)
    {
        givenFirstRectangle(args.firstRectangle);
        givenSecondRectangle(args.secondRectangle);

        whenAnalyzed();

        thenResponseStateIs(RectangleRelationship.SEPARATED);
        thenResponseSubstateIs(null);
        thenResponseIntersectionsIsEmpty();
    }

    @ParameterizedTest
    @MethodSource("provideContainedRectangles")
    public void givenContainedRectangles_WhenAnalyzed_ShouldReturnExpectedValues(TestArgs args)
    {
        givenFirstRectangle(args.firstRectangle);
        givenSecondRectangle(args.secondRectangle);

        whenAnalyzed();

        thenResponseStateIs(RectangleRelationship.CONTAINED);
        thenResponseSubstateIs(null);
        thenResponseIntersectionsIsEmpty();
    }

    @ParameterizedTest
    @MethodSource("provideAdjacentRectangles")
    public void givenAdjacentRectangles_WhenAnalyzed_ShouldReturnExpectedValues(TestArgs args)
    {
        givenFirstRectangle(args.firstRectangle);
        givenSecondRectangle(args.secondRectangle);

        whenAnalyzed();

        thenResponseStateIs(RectangleRelationship.ADJACENT);
        thenResponseSubstateIs(args.expectedSubState);
        thenResponseIntersectionsIsEmpty();
    }
    //endregion

    //region GivenWhenThen
    private void givenFirstRectangle(RectangleDTO firstRectangle)
    {
        this.firstRectangle = firstRectangle;
    }

    private void givenFirstRectangle(float x1, float y1, float x2, float y2)
    {
        this.firstRectangle = new RectangleDTO(x1, y1, x2, y2);
    }

    private void givenSecondRectangle(RectangleDTO secondRectangle)
    {
        this.secondRectangle = secondRectangle;
    }

    private void givenSecondRectangle(float x1, float y1, float x2, float y2)
    {
        this.secondRectangle = new RectangleDTO(x1, y1, x2, y2);
    }

    private void whenAnalyzed()
    {
        Rectangle a = RectangleFactory.create(firstRectangle);
        Rectangle b = RectangleFactory.create(secondRectangle);
        SortedPair<Rectangle> rectangles = new RectanglePair(a, b, Comparators.RECTANGLE_HORIZONTAL);
        response = rectangleService.analyze(rectangles);
    }

    private void thenResponseStateIs(final RectangleState expected)
    {
        assertEquals(expected, response.getState());
    }

    private void thenResponseSubstateIs(final RectangleSubState expected)
    {
        assertEquals(expected, response.getSubState());
    }

    private void thenResponseIntersectionsIs(List<PointDTO> expected)
    {
        expected.sort(POINT_DTO_COMPARATOR);
        assertEquals(expected, response.getIntersections());
    }

    private void thenResponseIntersectionsIsEmpty() {
        assertTrue(response.getIntersections().isEmpty());
    }
    //endregion

    //region Test Data Providers
    private static Stream<TestArgs> provideIntersectingRectangles()
    {
        return Stream.of(
            new TestArgsBuilder() // 1
                .withFirstRectangle(1, 1, 3, 3)
                .withSecondRectangle(2, 2, 4, 5)
                .withExpectedIntersection(3, 2)
                .withExpectedIntersection(2, 3)
                .build(),

            new TestArgsBuilder() // 2
                .withFirstRectangle(1, 3, 4, 6)
                .withSecondRectangle(3, 1, 6, 5)
                .withExpectedIntersection(3, 3)
                .withExpectedIntersection(4, 5)
                .build(),

            new TestArgsBuilder() // 3
                .withFirstRectangle(1, 1, 3, 4)
                .withSecondRectangle(2, 2, 4, 3)
                .withExpectedIntersection(3, 2)
                .withExpectedIntersection(3, 3)
                .build(),

            new TestArgsBuilder() // 4
                .withFirstRectangle(1, 1, 4, 3)
                .withSecondRectangle(2, 2, 3, 4)
                .withExpectedIntersection(2, 3)
                .withExpectedIntersection(3, 3)
                .build(),

            new TestArgsBuilder() // 5
                .withFirstRectangle(1, 2, 3, 3)
                .withSecondRectangle(2, 1, 4, 4)
                .withExpectedIntersection(2, 2)
                .withExpectedIntersection(2, 3)
                .build(),

            new TestArgsBuilder() // 6
                 .withFirstRectangle(2, 1, 4, 4)
                .withSecondRectangle(1, 2, 3, 3)
                .withExpectedIntersection(2, 2)
                .withExpectedIntersection(2, 3)
                .build(),

            new TestArgsBuilder() // 7
                .withFirstRectangle(-3, -3, -1, -1)
                .withSecondRectangle(-4, -4, -2, -2)
                .withExpectedIntersection(-3, -2)
                .withExpectedIntersection(-2, -3)
                .build()

        );
    }

    private static Stream<TestArgs> provideSeparatedRectangles()
    {
        return Stream.of(
                new TestArgsBuilder() // 1
                        .withFirstRectangle(1, 1, 3, 3)
                        .withSecondRectangle(4, 1, 5, 3)
                        .build(),

                new TestArgsBuilder() // 2
                        .withFirstRectangle(4, 1, 5, 3)
                        .withSecondRectangle(1, 1, 3, 3)
                        .build(),

                new TestArgsBuilder() // 3
                        .withFirstRectangle(1, 1, 2, 2)
                        .withSecondRectangle(1, 3, 2, 4)
                        .build(),

                new TestArgsBuilder() // 4
                        .withFirstRectangle(1, 1, 2, 2)
                        .withSecondRectangle(2, 2, 3, 3)
                        .build()

        );
    }

    private static Stream<TestArgs> provideContainedRectangles()
    {
        return Stream.of(
            new TestArgsBuilder() // 1
                .withFirstRectangle(1, 1, 4, 4)
                .withSecondRectangle(2, 2, 3, 3)
                .build(),

            new TestArgsBuilder() // 2
                .withFirstRectangle(2, 2, 3, 3)
                .withSecondRectangle(1, 1, 4, 4)
                .build(),

            new TestArgsBuilder() // 3
                .withFirstRectangle(1.0f, 1.0f, 4.0f, 4.0f)
                .withSecondRectangle(1.1f, 1.1f, 2.0f, 3.9f)
                .build()
        );
    }

    private static Stream<TestArgs> provideAdjacentRectangles()
    {
        return Stream.of(
            new TestArgsBuilder() // 1
                .withFirstRectangle(1, 1, 2, 2)
                .withSecondRectangle(2, 1, 3, 2)
                .withExpectedSubState(RectangleAdjacencyState.PROPER)
                .build(),

            new TestArgsBuilder() // 2
                .withFirstRectangle(2, 1, 3, 2)
                .withSecondRectangle(1, 1, 2, 2)
                .withExpectedSubState(RectangleAdjacencyState.PROPER)
                .build(),

            new TestArgsBuilder() // 3
                .withFirstRectangle(1, 1, 2, 2)
                .withSecondRectangle(1, 2, 2, 3)
                .withExpectedSubState(RectangleAdjacencyState.PROPER)
                .build(),

            new TestArgsBuilder() // 4
                .withFirstRectangle(1, 1, 3, 3)
                .withSecondRectangle(2, 3, 4, 4)
                .withExpectedSubState(RectangleAdjacencyState.PARTIAL)
                .build(),

            new TestArgsBuilder() // 5
                .withFirstRectangle(1, 1, 5, 2)
                .withSecondRectangle(2, 2, 3, 3)
                .withExpectedSubState(RectangleAdjacencyState.SUB_LINE)
                .build()
        );
    }
    //endregion

    //region TestArgs & Builder
    private static class TestArgs {
        public RectangleDTO firstRectangle;
        public RectangleDTO secondRectangle;
        public List<PointDTO> expectedIntersections = new ArrayList<>();
        public RectangleSubState expectedSubState;
    }

    private static class TestArgsBuilder {
        private final TestArgs args = new TestArgs();

        public TestArgsBuilder withFirstRectangle(float x1, float y1, float x2, float y2)
        {
            args.firstRectangle = new RectangleDTO(x1, y1, x2, y2);
            return this;
        }

        public TestArgsBuilder withSecondRectangle(float x1, float y1, float x2, float y2)
        {
            args.secondRectangle = new RectangleDTO(x1, y1, x2, y2);
            return this;
        }

        public TestArgsBuilder withExpectedIntersection(float x, float y)
        {
            args.expectedIntersections.add(new PointDTO(x, y));
            return this;
        }

        public TestArgsBuilder withExpectedSubState(RectangleSubState subState)
        {
            args.expectedSubState = subState;
            return this;
        }

        public TestArgs build()
        {
            return args;
        }
    }
    //endregion
}
