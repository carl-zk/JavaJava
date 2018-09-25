package com.hero.web.support.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author carl
 */
public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> implements
    ContextualSerializer {

  private static final long serialVersionUID = -4814530545129095341L;

  private final String format;

  @Override
  public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
    LocalDateTimeFormat annotation = property.getAnnotation(LocalDateTimeFormat.class);
    return new LocalDateTimeSerializer(annotation == null ? null : annotation.value());
  }

  @Override
  public void serialize(LocalDateTime localDateTime, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    if (StringUtils.isEmpty(format)) {
      gen.writeString(String.valueOf(Timestamp.valueOf(localDateTime).getTime()));
    } else {
      gen.writeString(localDateTime.format(DateTimeFormatter.ofPattern(format)));
    }
  }

  public LocalDateTimeSerializer() {
    super(LocalDateTime.class);
    this.format = null;
  }

  public LocalDateTimeSerializer(String value) {
    super(LocalDateTime.class);
    this.format = value;
  }
}
