package com.propertyRead.utl.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by deshanchathusanka on 4/5/18.
 */
public class DateTimeDeserializer extends StdScalarDeserializer<LocalDateTime> {
    public DateTimeDeserializer()
    {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime=jsonParser.getText().trim();
        LocalDateTime localDateTime=LocalDateTime.parse(dateTime,formatter);
        return localDateTime;
    }
}
