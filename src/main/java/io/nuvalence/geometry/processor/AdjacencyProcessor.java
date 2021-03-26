package io.nuvalence.geometry.processor;

import io.nuvalence.geometry.dto.AnalyzeRectangleResponse;
import io.nuvalence.geometry.model.Line;
import io.nuvalence.geometry.model.Rectangle;
import io.nuvalence.geometry.service.RectangleAdjacencyState;
import io.nuvalence.geometry.service.RectangleRelationship;
import io.nuvalence.geometry.service.RectangleSubState;
import io.nuvalence.geometry.util.Comparators;
import io.nuvalence.geometry.model.SortedPair;
import io.nuvalence.geometry.util.LineExt;
import org.springframework.stereotype.Service;

@Service
public class AdjacencyProcessor implements Processor<Rectangle> {
    @Override
    public boolean process(final SortedPair<Rectangle> rectangles, final AnalyzeRectangleResponse response)
    {
        rectangles.sort(Comparators.RECTANGLE_HORIZONTAL);
        final Rectangle left = rectangles.first();
        final Rectangle right= rectangles.second();
        if (LineExt.isAdjacentTo(left.getRightLine(), right.getLeftLine()))
        {
            response.setState(RectangleRelationship.ADJACENT);
            response.setSubState(determineAdjacency(left.getRightLine(), right.getLeftLine()));
            return true;
        }

        rectangles.sort(Comparators.RECTANGLE_VERTICAL);
        final Rectangle lower = rectangles.first();
        final Rectangle upper = rectangles.second();
        if (LineExt.isAdjacentTo(lower.getTopLine(), upper.getBottomLine()))
        {
            response.setState(RectangleRelationship.ADJACENT);
            response.setSubState(determineAdjacency(lower.getTopLine(), upper.getBottomLine()));
            return true;
        }

        response.setState(RectangleRelationship.SEPARATED);
        return true;
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
}
