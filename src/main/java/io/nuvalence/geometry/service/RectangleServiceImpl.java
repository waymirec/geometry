package io.nuvalence.geometry.service;

import io.nuvalence.geometry.dto.AnalyzeRectangleRequest;
import io.nuvalence.geometry.dto.AnalyzeRectangleResponse;
import io.nuvalence.geometry.factory.RectangleFactory;
import io.nuvalence.geometry.model.Rectangle;
import io.nuvalence.geometry.util.RectangleAnalyzer;
import org.springframework.stereotype.Service;

@Service
public class RectangleServiceImpl implements RectangleService {
    public AnalyzeRectangleResponse analyze(final AnalyzeRectangleRequest request)
    {
        final Rectangle first = RectangleFactory.create(request.getFirst());
        final Rectangle second = RectangleFactory.create(request.getSecond());
        return RectangleAnalyzer.analyze(first, second);
    }
}
