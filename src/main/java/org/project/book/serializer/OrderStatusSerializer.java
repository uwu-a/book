package org.project.book.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.project.book.enums.OrderStatus;

import java.io.IOException;

public class OrderStatusSerializer extends JsonSerializer<Integer> {
    @Override
    public void serialize(Integer integer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(OrderStatus.getDescriptionByCode(integer));
    }
}
