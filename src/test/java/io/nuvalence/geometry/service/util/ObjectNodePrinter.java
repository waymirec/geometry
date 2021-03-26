package io.nuvalence.geometry.service.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.nuvalence.geometry.util.ExceptionHelper;

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
