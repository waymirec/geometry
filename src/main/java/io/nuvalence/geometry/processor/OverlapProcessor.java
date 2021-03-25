package io.nuvalence.geometry.processor;

import io.nuvalence.geometry.dto.AnalyzeRectangleResponse;
import io.nuvalence.geometry.dto.PointDTO;
import io.nuvalence.geometry.model.Line;
import io.nuvalence.geometry.model.Point;
import io.nuvalence.geometry.model.Rectangle;
import io.nuvalence.geometry.service.RectangleRelationship;
import io.nuvalence.geometry.service.RectangleState;
import io.nuvalence.geometry.util.Comparators;
import io.nuvalence.geometry.util.SortedPair;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OverlapProcessor implements Processor<Rectangle> {
    private static final Type POINT_DTO_TYPE = new TypeToken<List<PointDTO>>() {}.getType();
    private final ModelMapper mapper;

    public OverlapProcessor(@Autowired final ModelMapper mapper)
    {
        this.mapper = mapper;
    }

    @Override
    public boolean process(final SortedPair<Rectangle> rectangles, final AnalyzeRectangleResponse response)
    {
        if (rectangles.first().equals(rectangles.second()))
        {
            response.setState(RectangleRelationship.CONTAINED);
            return true;
        }

        if (!rectangles.first().overlaps(rectangles.second())) return false;

        List<Point> intersections = inspectSides(rectangles);
        final RectangleState state = intersections.isEmpty()
                ? RectangleRelationship.CONTAINED
                : RectangleRelationship.INTERSECTED;
        response.setState(state);

        Collections.sort(intersections);
        response.setIntersections(mapper.map(intersections, POINT_DTO_TYPE));

        return true;
    }

    private static List<Point> inspectSides(final SortedPair<Rectangle> rectangles)
    {
        rectangles.sort(Comparators.RECTANGLE_HORIZONTAL);
        final Rectangle left = rectangles.first();
        final Rectangle right = rectangles.second();
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
