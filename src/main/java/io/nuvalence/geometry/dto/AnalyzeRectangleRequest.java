package io.nuvalence.geometry.dto;

import java.util.List;

public class AnalyzeRectangleRequest {
    private RectangleDTO first;
    private RectangleDTO second;

    public AnalyzeRectangleRequest(final RectangleDTO first, final RectangleDTO second)
    {
        this.first = first;
        this.second = second;
    }

    public AnalyzeRectangleRequest()
    {

    }

    public RectangleDTO getFirst() {
        return first;
    }

    public void setFirst(RectangleDTO first) {
        this.first = first;
    }

    public RectangleDTO getSecond() {
        return second;
    }

    public void setSecond(RectangleDTO second) {
        this.second = second;
    }
}
