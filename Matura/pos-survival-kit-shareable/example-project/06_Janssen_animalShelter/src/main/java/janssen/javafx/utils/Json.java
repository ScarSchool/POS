package janssen.javafx.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class Json {

  protected static ObjectMapper json;

  static {
    json = new ObjectMapper();
    json.findAndRegisterModules();
    json.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
    json.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    json.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  }

  public static String stringify(Object value) throws JsonProcessingException {
    return json.writeValueAsString(value);
  }

  public static <T> T parse(String jsonString, Class<T> type) throws JsonProcessingException {
    return json.readValue(jsonString, type);
  }

  public static <T> List<T> parseList(String jsonString, Class<T> type) throws JsonProcessingException {
    final CollectionType collType = json.getTypeFactory().constructCollectionType(List.class, type);
    return json.readValue(jsonString, collType);
  }

}
