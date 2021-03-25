package io.nuvalence.geometry.processor;

import io.nuvalence.geometry.dto.AnalyzeRectangleResponse;
import io.nuvalence.geometry.model.Shape;
import io.nuvalence.geometry.util.SortedPair;

public interface Processor<T extends Shape> {
    boolean process(final SortedPair<T> pair, final AnalyzeRectangleResponse response);
}
