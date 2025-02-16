package com.asshofa.management.util.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeSerializer extends JsonSerializer<LocalTime> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public void serialize(LocalTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.format(formatter));
    }
}
