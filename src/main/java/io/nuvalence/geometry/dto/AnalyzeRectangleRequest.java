package io.nuvalence.geometry.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AnalyzeRectangleRequest {
    @ApiModelProperty(notes = "First rectangle to compare")
    @JsonProperty(required = true)
    private RectangleDTO first;
    @ApiModelProperty(notes = "Second rectangle to compare")
    @JsonProperty(required = true)
    private RectangleDTO second;

    @JsonCreator
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
