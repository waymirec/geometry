package io.nuvalence.geometry.util;

import io.nuvalence.geometry.dto.AnalyzeRectangleResponse;
import io.nuvalence.geometry.dto.PointDTO;
import io.nuvalence.geometry.model.Line;
import io.nuvalence.geometry.model.Point;
import io.nuvalence.geometry.model.Rectangle;
import io.nuvalence.geometry.model.Rectangles;
import io.nuvalence.geometry.service.RectangleAdjacencyState;
import io.nuvalence.geometry.service.RectangleState;
import io.nuvalence.geometry.service.RectangleRelationship;
import io.nuvalence.geometry.service.RectangleSubState;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final public class RectangleAnalyzer {
    private static final ModelMapper mapper = new ModelMapper();
    private static final Type POINT_DTO_TYPE = new TypeToken<List<PointDTO>>() {}.getType();

    private RectangleAnalyzer() {}

    public static AnalyzeRectangleResponse analyze(final Rectangle r1, final Rectangle r2)
    {
        AnalyzeRectangleResponse response = new AnalyzeRectangleResponse();

        if (r1.equals(r2))
        {
            response.setState(RectangleRelationship.CONTAINED);
            return response;
        }

        final Rectangles rectangles = new Rectangles(r1, r2);
        final Rectangle left = rectangles.getLeft();
        final Rectangle right = rectangles.getRight();
        final Rectangle lower = rectangles.getLower();
        final Rectangle upper = rectangles.getUpper();

        if (rectangles.getLeft().overlaps(rectangles.getRight()))
        {
            List<Point> intersections = inspectLines(rectangles);
            final RectangleState state = intersections.isEmpty()
                    ? RectangleRelationship.CONTAINED
                    : RectangleRelationship.INTERSECTED;
            response.setState(state);

            Collections.sort(intersections);
            response.setIntersections(mapper.map(intersections, POINT_DTO_TYPE));
            return response;
        }


        determineAdjacency(rectangles, response);

        response.setState(RectangleRelationship.SEPARATED);
        return response;
    }

    private static void determineAdjacency(final Rectangles rectangles, AnalyzeRectangleResponse response)
    {
        final Rectangle left = rectangles.getLeft();
        final Rectangle right = rectangles.getRight();
        final Rectangle lower = rectangles.getLower();
        final Rectangle upper = rectangles.getUpper();

        response.setState(RectangleRelationship.ADJACENT);
        if(left.getRightLine().isAdjacentTo(right.getLeftLine()))
        {
            response.setSubState(determineAdjacency(left.getRightLine(), right.getLeftLine()));
            return;
        }
        else if (lower.getTopLine().isAdjacentTo(upper.getBottomLine()))
        {
            response.setSubState(determineAdjacency(lower.getTopLine(), upper.getBottomLine()));
            return;
        }

        response.setState(RectangleRelationship.SEPARATED);
        return null;
    }

    private static RectangleSubState determineAdjacency(final Line line1, final Line line2)
    {
        if (line1.equals(line2)) return RectangleAdjacencyState.PROPER;

        if (line1.isHorizontal())
        {
            if (line1.getFirstPoint().getX() < line2.getFirstPoint().getX() && line1.getSecondPoint().getX() > line2.getSecondPoint().getX()) return RectangleAdjacencyState.SUB_LINE;
        }

        if (line1.getFirstPoint().getY() < line2.getFirstPoint().getY() && line1.getSecondPoint().getY() > line2.getSecondPoint().getY()) return RectangleAdjacencyState.SUB_LINE;
        return RectangleAdjacencyState.PARTIAL;
    }

    private static List<Point> inspectLines(final Rectangles rectangles)
    {
        final Rectangle left = rectangles.getLeft();
        final Rectangle right = rectangles.getRight();
        List<Point> intersections = new ArrayList<>();

        discoverIntersections(left.getRightLine(), right.getBottomLine(), intersections);
        discoverIntersections(left.getRightLine(), right.getTopLine(), intersections);
        discoverIntersections(left.getTopLine(), right.getLeftLine(), intersections);
        discoverIntersections(left.getTopLine(), right.getRightLine(), intersections);
        discoverIntersections(left.getBottomLine(), right.getLeftLine(), intersections);
        discoverIntersections(left.getBottomLine(), right.getRightLine(), intersections);

        return intersections;
    }

    private static void discoverIntersections(final Line l1, final Line l2, List<Point> intersections)
    {
        if (l1.intersectsWith(l2))
        {
            intersections.add(l1.findIntersection(l2));
        }
    }
}
