package io.nuvalence.geometry.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.nuvalence.geometry.util.ObjectNodePrinter;

public class AnalyzeRectangleRequestBuilder {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String FIRST = "first";
    private static final String SECOND = "second";

    private final ObjectNode node = MAPPER.createObjectNode();


    public AnalyzeRectangleRequestBuilder withFirst(ObjectNode first)
    {
        node.set(FIRST, first);
        return this;
    }

    public AnalyzeRectangleRequestBuilder withFirst(String first)
    {
        node.put(FIRST, first);
        return this;
    }

    public AnalyzeRectangleRequestBuilder withSecond(ObjectNode second)
    {
        node.set(SECOND, second);
        return this;
    }

    public AnalyzeRectangleRequestBuilder withSecond(String second)
    {
        node.put(SECOND, second);
        return this;
    }

    public AnalyzeRectangleRequestBuilder with(String key, String value)
    {
        node.put(key, value);
        return this;
    }

    public String build()
    {
        return ObjectNodePrinter.toString(node);
    }
}
