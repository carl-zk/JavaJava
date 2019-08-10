package com.carl.web.support.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author carl
 */
public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> implements
        ContextualDeserializer {

    private static final long serialVersionUID = 9195285301412750759L;

    private final String format;

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) {
        LocalDateTimeFormat annotation = property.getAnnotation(LocalDateTimeFormat.class);
        return new LocalDateTimeDeserializer(annotation == null ? null : annotation.value());
    }

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String string = p.getText();
        if (StringUtils.isEmpty(format)) {
            Timestamp timestamp = new Timestamp(Long.parseLong(string));
            return timestamp.toLocalDateTime();
        } else {
            return LocalDateTime.parse(string, DateTimeFormatter.ofPattern(format));
        }
    }

    public LocalDateTimeDeserializer() {
        super(LocalDateTime.class);
        this.format = null;
    }

    public LocalDateTimeDeserializer(String format) {
        super(LocalDateTime.class);
        this.format = format;
    }
}
