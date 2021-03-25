package io.nuvalence.geometry.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class FloatSerializer extends JsonSerializer<Float> {
    @Override
    public void serialize(Float value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        double roundedValue = value*10000;
        roundedValue = Math.round(roundedValue );
        roundedValue = roundedValue /10000;
        generator.writeNumber(roundedValue );
    }
}
