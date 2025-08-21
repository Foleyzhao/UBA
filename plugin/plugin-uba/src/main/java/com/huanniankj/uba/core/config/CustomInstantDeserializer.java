package com.huanniankj.uba.core.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 自定义 Instant 反序列化器
 *
 * @author happynewyear
 */
public class CustomInstantDeserializer extends StdDeserializer<Instant> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    protected CustomInstantDeserializer() {
        super(Instant.class);
    }

    @Override
    public Instant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JacksonException {
        try {
            String dateStr = jsonParser.getText();
            LocalDateTime localDateTime = LocalDateTime.parse(dateStr, FORMATTER);
            return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        } catch (Exception e) {
            Long dateLong = jsonParser.getLongValue();
            return Instant.ofEpochSecond(dateLong);
        }
    }
}
