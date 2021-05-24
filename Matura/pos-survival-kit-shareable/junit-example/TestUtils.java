package janssen.app;

import janssen.app.models.Country;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.function.Function;

public class TestUtils {

  public static Matcher<Iterable<?>> containsInAnyOrder(Collection<Country> collection, Function<Country, ?> fieldGetter) {
    return Matchers.containsInAnyOrder(collection.stream().map(fieldGetter).toArray());
  }

  public static String toJsonFormat(LocalTime time) {
    return time.format(DateTimeFormatter.ISO_TIME);
  }

  public static String toJsonFormat(LocalDate date) {
    return date.toString();
  }

}
