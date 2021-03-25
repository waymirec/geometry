package io.nuvalence.geometry.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.nuvalence.geometry.service.RectangleState;
import io.nuvalence.geometry.service.RectangleSubState;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnalyzeRectangleResponse {
    @ApiModelProperty(notes = "Relationship between the two provided rectangles")
    private RectangleState state;
    @ApiModelProperty(notes = "Additional data (if available)")
    private RectangleSubState subState;
    @ApiModelProperty(notes = "Points of intersection between the two rectangles (if any)")
    private List<PointDTO> intersections = new ArrayList<>();

    public RectangleState getState() {
        return state;
    }

    public void setState(RectangleState state) {
        this.state = state;
    }

    public RectangleSubState getSubState() {
        return subState;
    }

    public void setSubState(RectangleSubState subState) {
        this.subState = subState;
    }

    public List<PointDTO> getIntersections() {
        return intersections;
    }

    public void setIntersections(List<PointDTO> intersections) {
        this.intersections = intersections;
    }
}
