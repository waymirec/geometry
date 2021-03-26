package io.nuvalence.geometry.service;

import io.nuvalence.geometry.dto.AnalyzeRectangleResponse;
import io.nuvalence.geometry.model.Rectangle;
import io.nuvalence.geometry.model.SortedPair;

public interface RectangleService {
    AnalyzeRectangleResponse analyze(SortedPair<Rectangle> rectangles);
}
