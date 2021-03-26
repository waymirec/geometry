package io.nuvalence.geometry.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ObjectNodePrinter {
    private ObjectNodePrinter() { }

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String toString(final ObjectNode node)
    {
        try
        {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(node);
        } catch(JsonProcessingException jsonProcessingException)
        {
            throw ExceptionHelper.softenException(jsonProcessingException);
        }
    }
}
