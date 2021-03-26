package io.nuvalence.geometry.service;

import io.nuvalence.geometry.dto.AnalyzeRectangleResponse;
import io.nuvalence.geometry.model.Rectangle;
import io.nuvalence.geometry.processor.AdjacencyProcessor;
import io.nuvalence.geometry.processor.OverlapProcessor;
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

    public AnalyzeRectangleResponse analyze(final SortedPair<Rectangle> rectangles)
    {
        final AnalyzeRectangleResponse response = new AnalyzeRectangleResponse();
        boolean complete = overlapProcessor.process(rectangles, response);
        if (!complete) adjacencyProcessor.process(rectangles, response);

        return response;
    }
}
