package com.propertyRead.utl.serializer;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by deshanchathusanka on 4/5/18.
 */
public class DateTimeSerializer extends StdScalarSerializer<LocalDateTime> {

    public DateTimeSerializer()
    {
        super(LocalDateTime.class);
    }

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeStr=localDateTime.format(formatter);
        jsonGenerator.writeString(dateTimeStr);
    }
}
