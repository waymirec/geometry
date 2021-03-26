package io.nuvalence.geometry.controller;

import io.nuvalence.geometry.dto.AnalyzeRectangleRequest;
import io.nuvalence.geometry.factory.RectangleFactory;
import io.nuvalence.geometry.model.Rectangle;
import io.nuvalence.geometry.util.Comparators;
import io.nuvalence.geometry.util.RectanglePair;
import io.nuvalence.geometry.util.SortedPair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CacheKeyGenerator {
    private static final Logger LOGGER = LogManager.getLogger(CacheKeyGenerator.class);

    public static String generate(final AnalyzeRectangleRequest request)
    {
        final Rectangle first = RectangleFactory.create(request.getFirst());
        final Rectangle second = RectangleFactory.create(request.getSecond());
        final SortedPair<Rectangle> rectangles = new RectanglePair(first, second, Comparators.RECTANGLE_HORIZONTAL);
        final String key = String.format("{%.2f,%.2f},{%.2f,%.2f}:{%.2f,%.2f},{%.2f,%.2f}",
                rectangles.first().getLowerLeft().getX(),
                rectangles.first().getLowerLeft().getY(),
                rectangles.first().getUpperRight().getX(),
                rectangles.first().getUpperRight().getY(),
                rectangles.second().getLowerLeft().getX(),
                rectangles.second().getLowerLeft().getY(),
                rectangles.second().getUpperRight().getX(),
                rectangles.second().getUpperRight().getY());

        LOGGER.debug(() -> "generated cache key: " + key);
        return key;
    }
}
