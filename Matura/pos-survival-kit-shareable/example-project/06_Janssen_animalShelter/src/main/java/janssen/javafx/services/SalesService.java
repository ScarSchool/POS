package janssen.javafx.services;

import janssen.javafx.models.Sales;
import janssen.javafx.utils.Http;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SalesService {

  public static final String ENDPOINT = "/sales";

  public static CompletableFuture<List<Sales>> listAll() {
    return Http.getCollection(ENDPOINT, Sales.class);
  }

  public static CompletableFuture<Sales> get(long id) {
    return Http.get(ENDPOINT + "/" + URLEncoder.encode("" + id, StandardCharsets.UTF_8).replaceAll("[+]", "%20"), Sales.class);
  }

  public static CompletableFuture<Sales> create(Sales sales) throws JsonProcessingException {
    return Http.post(ENDPOINT, sales, Sales.class);
  }

  public static CompletableFuture<Void> update(long id, Sales sales) throws JsonProcessingException {
    return Http.put(ENDPOINT + "/" + URLEncoder.encode("" + id, StandardCharsets.UTF_8).replaceAll("[+]", "%20"), sales);
  }

  public static CompletableFuture<Void> delete(long id) {
    return Http.delete(ENDPOINT + "/" + URLEncoder.encode("" + id, StandardCharsets.UTF_8).replaceAll("[+]", "%20"));
  }

}
