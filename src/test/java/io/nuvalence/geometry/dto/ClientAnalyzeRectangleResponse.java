package io.nuvalence.geometry.dto;

import java.util.ArrayList;
import java.util.List;

public class ClientAnalyzeRectangleResponse {
    public String state;
    public String subState;
    public List<PointDTO> intersections = new ArrayList<>();
}
