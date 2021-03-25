package io.nuvalence.geometry.service;

import io.nuvalence.geometry.dto.AnalyzeRectangleRequest;
import io.nuvalence.geometry.dto.AnalyzeRectangleResponse;

public interface RectangleService {
    AnalyzeRectangleResponse analyze(AnalyzeRectangleRequest request);
}
