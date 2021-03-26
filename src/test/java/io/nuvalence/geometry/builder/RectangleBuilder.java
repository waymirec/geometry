package io.nuvalence.geometry.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class RectangleBuilder {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String LLX = "lowerLeftX";
    private static final String LLY = "lowerLeftY";
    private static final String URX = "upperRightX";
    private static final String URY = "upperRightY";

    private final ObjectNode node = MAPPER.createObjectNode();

    public RectangleBuilder withLowerLeftX(float x)
    {
        node.put(LLX, x);
        return this;
    }

    public RectangleBuilder withLowerLeftX(String x)
    {
        node.put(LLX, x);
        return this;
    }

    public RectangleBuilder withLowerLeftY(float x)
    {
        node.put(LLY, x);
        return this;
    }

    public RectangleBuilder withLowerLeftY(String x)
    {
        node.put(LLY, x);
        return this;
    }

    public RectangleBuilder withUpperRightX(float x)
    {
        node.put(URX, x);
        return this;
    }

    public RectangleBuilder withUpperRightX(String x)
    {
        node.put(URX, x);
        return this;
    }

    public RectangleBuilder withUpperRightY(float y)
    {
        node.put(URY, y);
        return this;
    }

    public RectangleBuilder withUpperRightY(String y)
    {
        node.put(URY, y);
        return this;
    }

    public RectangleBuilder with(String key, String value)
    {
        node.put(key, value);
        return this;
    }

    public ObjectNode build()
    {
        return node;
    }
}
