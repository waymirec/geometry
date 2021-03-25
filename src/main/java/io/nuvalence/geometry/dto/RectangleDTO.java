package io.nuvalence.geometry.dto;

public class RectangleDTO {
    private float lowerLeftX;
    private float lowerLeftY;
    private float upperRightX;
    private float upperRightY;

    public RectangleDTO(final float x1, final float y1, final float x2, final float y2)
    {
        this.lowerLeftX = x1;
        this.lowerLeftY = y1;
        this.upperRightX = x2;
        this.upperRightY = y2;
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
