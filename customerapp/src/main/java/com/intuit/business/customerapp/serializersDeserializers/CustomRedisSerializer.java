package com.intuit.business.customerapp.serializersDeserializers;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class CustomRedisSerializer implements RedisSerializer<CustomRedisSerializer> {

private Converter<Object, byte[]> serializer = new SerializingConverter();
private Converter<byte[], Object> deserializer = new DeserializingConverter();

static final byte[] EMPTY_ARRAY = new byte[0];

public CustomRedisSerializer deserialize(byte[] bytes) {
    if (isEmpty(bytes)) {
        return null;
    }

    try {
        return (CustomRedisSerializer) deserializer.convert(bytes);
    } catch (Exception ex) {
        throw new SerializationException("Cannot deserialize", ex);
    }
}

public byte[] serialize(CustomRedisSerializer object) {
    if (object == null) {
        return EMPTY_ARRAY;
    }

    try {
        return serializer.convert(object);
    } catch (Exception ex) {
        return EMPTY_ARRAY;

    }
}

private boolean isEmpty(byte[] data) {
    return (data == null || data.length == 0);
}
}
