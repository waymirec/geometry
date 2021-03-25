package io.nuvalence.geometry.service;

import io.nuvalence.geometry.dto.AnalyzeRectangleRequest;
import io.nuvalence.geometry.dto.AnalyzeRectangleResponse;
import io.nuvalence.geometry.factory.RectangleFactory;
import io.nuvalence.geometry.model.Rectangle;
import io.nuvalence.geometry.processor.AdjacencyProcessor;
import io.nuvalence.geometry.processor.OverlapProcessor;
import io.nuvalence.geometry.util.Comparators;
import io.nuvalence.geometry.util.RectanglePair;
import io.nuvalence.geometry.util.SortedPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RectangleServiceImpl implements RectangleService {
    private final OverlapProcessor overlapProcessor;
    private final AdjacencyProcessor adjacencyProcessor;

    public RectangleServiceImpl(@Autowired final OverlapProcessor overlapProcessor,
                                @Autowired final AdjacencyProcessor adjacencyProcessor)
    {
        this.overlapProcessor = overlapProcessor;
        this.adjacencyProcessor = adjacencyProcessor;
    }

    public AnalyzeRectangleResponse analyze(final AnalyzeRectangleRequest request)
    {
        final AnalyzeRectangleResponse response = new AnalyzeRectangleResponse();
        final Rectangle first = RectangleFactory.create(request.getFirst());
        final Rectangle second = RectangleFactory.create(request.getSecond());
        final SortedPair<Rectangle> rectangles = new RectanglePair(first, second, Comparators.RECTANGLE_HORIZONTAL);

        boolean complete = overlapProcessor.process(rectangles, response);
        if (!complete) adjacencyProcessor.process(rectangles, response);

        return response;
    }
}
