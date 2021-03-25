package io.nuvalence.geometry.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class RectangleDTO {
    @ApiModelProperty(notes = "Rectangle's lower-left Y coordinate")
    @JsonProperty(required = true)
    private float lowerLeftX;
    @ApiModelProperty(notes = "Rectangle's lower-left Y coordinate")
    @JsonProperty(required = true)
    private float lowerLeftY;
    @ApiModelProperty(notes = "Rectangle's upper-right X coordinate")
    @JsonProperty(required = true)
    private float upperRightX;
    @ApiModelProperty(notes = "Rectangle's upper-right Y coordinate")
    @JsonProperty(required = true)
    private float upperRightY;

    @JsonCreator
    public RectangleDTO(final float lowerLeftX, final float lowerLeftY, final float upperRightX, final float upperRightY)
    {
        this.lowerLeftX = lowerLeftX;
        this.lowerLeftY = lowerLeftY;
        this.upperRightX = upperRightX;
        this.upperRightY = upperRightY;
    }

    public RectangleDTO()
    {

    }
    public float getLowerLeftX() {
        return lowerLeftX;
    }

    public void setLowerLeftX(float lowerLeftX) {
        this.lowerLeftX = lowerLeftX;
    }

    public float getLowerLeftY() {
        return lowerLeftY;
    }

    public void setLowerLeftY(float lowerLeftY) {
        this.lowerLeftY = lowerLeftY;
    }

    public float getUpperRightX() {
        return upperRightX;
    }

    public void setUpperRightX(float upperRightX) {
        this.upperRightX = upperRightX;
    }

    public float getUpperRightY() {
        return upperRightY;
    }

    public void setUpperRightY(float upperRightY) {
        this.upperRightY = upperRightY;
    }
}
