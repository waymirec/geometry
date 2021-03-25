package io.nuvalence.geometry.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.nuvalence.geometry.config.FloatSerializer;

public class PointDTO {
    @JsonSerialize(using = FloatSerializer.class)
    private float x;
    @JsonSerialize(using = FloatSerializer.class)
    private float y;

    public PointDTO(final float x, final float y)
    {
        this.x = x;
        this.y = y;
    }

    public PointDTO()
    {

    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString()
    {
        return String.format("(%.2f,%.2f)", x, y);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int)x;
        hash = 31 * hash + (int)y;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof PointDTO))
            return false;

        PointDTO other = (PointDTO)o;
        return other.x == x && other.y == y;
    }
}
