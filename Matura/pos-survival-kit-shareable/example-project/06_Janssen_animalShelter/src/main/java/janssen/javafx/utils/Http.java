package janssen.javafx.utils;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;


public class Http {
  
  public static String BASE_URL = "http://localhost:8080";
  protected static HttpClient http = HttpClient.newHttpClient();
  protected static String APPLICATION_JSON = "application/json";

  /**
   * Gets a collection with objects of the given {@code responseType} from the given {@code endpoint}
   * @return a completable future with the collection
   */
  public static <T> CompletableFuture<List<T>> getCollection(String endpoint, Class<T> responseType) {
    final HttpRequest req = HttpRequest
      .newBuilder(URI.create(BASE_URL + endpoint))
      .GET()
      .build();

    return http.sendAsync(req, BodyHandlers.ofString())
      .handle((res, httpEx) -> {
        if (httpEx != null) throw new CompletionException(httpEx);

        try {
          if (res.statusCode() != 200) throw Json.parse(res.body(), SpringError.class);
          return Json.parseList(res.body(), responseType); 
        }
        catch (Exception ex) {
          throw new CompletionException(ex);
        }
      });
  }

  /**
   * Gets a single object of the given {@code responseType} from the given {@code endpoint}
   * @return a completable future with the object
   */
  public static <T> CompletableFuture<T> get(String endpoint, Class<T> responseType) {
    final HttpRequest req = HttpRequest
      .newBuilder(URI.create(BASE_URL + endpoint))
      .GET()
      .build();

    return http.sendAsync(req, BodyHandlers.ofString())
      .handle((res, httpEx) -> {
        if (httpEx != null) throw new CompletionException(httpEx);

        try {
          if (res.statusCode() != 200) throw Json.parse(res.body(), SpringError.class);
          return Json.parse(res.body(), responseType); 
        }
        catch (Exception ex) {
          throw new CompletionException(ex);
        }
      });
  }

  /**
   * Posts the given {@code body} to the given {@code endpoint}
   */
  public static CompletableFuture<Void> post(String endpoint, Object body) throws JsonProcessingException {
    final HttpRequest req = HttpRequest
      .newBuilder(URI.create(BASE_URL + endpoint))
      .header("Content-Type", APPLICATION_JSON)
      .POST(BodyPublishers.ofString(Json.stringify(body)))
      .build();

    return http.sendAsync(req, BodyHandlers.ofString())
      .handle((res, httpEx) -> {
        if (httpEx != null) throw new CompletionException(httpEx);

        try {
          if (res.statusCode() != 201) throw Json.parse(res.body(), SpringError.class);
        }
        catch (Exception ex) {
          throw new CompletionException(ex);
        }
        return null;
      });
  }

  /**
   * Posts the given {@code body} to the given {@code endpoint}
   * @return a completable future with the response of type {@type}
   */
  public static <T> CompletableFuture<T> post(String endpoint, Object body, Class<T> responseType) throws JsonProcessingException {
    final HttpRequest req = HttpRequest
      .newBuilder(URI.create(BASE_URL + endpoint))
      .header("Content-Type", APPLICATION_JSON)
      .POST(BodyPublishers.ofString(Json.stringify(body)))
      .build();

    return http.sendAsync(req, BodyHandlers.ofString())
      .handle((res, httpEx) -> {
        if (httpEx != null) throw new CompletionException(httpEx);

        try {
          if (res.statusCode() != 201) throw Json.parse(res.body(), SpringError.class);
          return Json.parse(res.body(), responseType); 
        }
        catch (Exception ex) {
          throw new CompletionException(ex);
        }
      });
  }

  /**
   * Puts the given {@code body} to the given {@code endpoint}
   */
  public static CompletableFuture<Void> put(String endpoint, Object body) throws JsonProcessingException {
    final HttpRequest req = HttpRequest
      .newBuilder(URI.create(BASE_URL + endpoint))
      .header("Content-Type", APPLICATION_JSON)
      .PUT(BodyPublishers.ofString(Json.stringify(body)))
      .build();

    return http.sendAsync(req, BodyHandlers.ofString())
      .handle((res, httpEx) -> {
        if (httpEx != null) throw new CompletionException(httpEx);

        try {
          if (res.statusCode() != 200) throw Json.parse(res.body(), SpringError.class);
        }
        catch (Exception ex) {
          throw new CompletionException(ex);
        }
        return null;
      });
  }

  /**
   * Puts the given {@code body} to the given {@code endpoint}
   * @return a completable future with the response of type {@type}
   */
  public static <T> CompletableFuture<T> put(String endpoint, Object body, Class<T> responseType) throws JsonProcessingException {
    final HttpRequest req = HttpRequest
      .newBuilder(URI.create(BASE_URL + endpoint))
      .header("Content-Type", APPLICATION_JSON)
      .PUT(BodyPublishers.ofString(Json.stringify(body)))
      .build();

    return http.sendAsync(req, BodyHandlers.ofString())
      .handle((res, httpEx) -> {
        if (httpEx != null) throw new CompletionException(httpEx);

        try {
          if (res.statusCode() != 200) throw Json.parse(res.body(), SpringError.class);
          return Json.parse(res.body(), responseType); 
        }
        catch (Exception ex) {
          throw new CompletionException(ex);
        }
      });
  }

  /**
   * Deletes the given {@code endpoint}
   */
  public static CompletableFuture<Void> delete(String endpoint) {
    final HttpRequest req = HttpRequest
      .newBuilder(URI.create(BASE_URL + endpoint))
      .DELETE()
      .build();

    return http.sendAsync(req, BodyHandlers.ofString())
      .handle((res, httpEx) -> {
        if (httpEx != null) throw new CompletionException(httpEx);

        try {
          if (res.statusCode() != 204) throw Json.parse(res.body(), SpringError.class);
        }
        catch (Exception ex) {
          throw new CompletionException(ex);
        }
        return null;
      });
  }

  /**
   * Deletes the given {@code endpoint}
   * @return a completable future with the response of type {@type}
   */
  public static <T> CompletableFuture<T> delete(String endpoint, Class<T> responseType) {
    final HttpRequest req = HttpRequest
      .newBuilder(URI.create(BASE_URL + endpoint))
      .DELETE()
      .build();

    return http.sendAsync(req, BodyHandlers.ofString())
      .handle((res, httpEx) -> {
        if (httpEx != null) throw new CompletionException(httpEx);

        try {
          if (res.statusCode() != 204) throw Json.parse(res.body(), SpringError.class);
          return Json.parse(res.body(), responseType); 
        }
        catch (Exception ex) {
          throw new CompletionException(ex);
        }
      });
  }

}
