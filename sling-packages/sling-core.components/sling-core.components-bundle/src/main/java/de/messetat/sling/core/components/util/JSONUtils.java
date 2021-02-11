package de.messetat.sling.core.components.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * JSON Utils for serialization.
 */
public final class JSONUtils {

    private JSONUtils() {
        throw new IllegalAccessError("JSONUtils.class");
    }

    public static String serializeToJSON(Object serializable) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writer().writeValueAsString(serializable);
    }

    public static <T> T deserializeToObject(String serializable, Class<T> type) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return (T) mapper.readerFor(type).readValue(serializable);
    }


}
