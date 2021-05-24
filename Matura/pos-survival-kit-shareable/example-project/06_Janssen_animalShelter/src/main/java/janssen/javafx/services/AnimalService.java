package janssen.javafx.services;

import janssen.javafx.models.Animal;
import janssen.javafx.utils.Http;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AnimalService {

  public static final String ENDPOINT = "/animals";

  public static CompletableFuture<List<Animal>> listAll() {
    return Http.getCollection(ENDPOINT, Animal.class);
  }

  public static CompletableFuture<Animal> get(long id) {
    return Http.get(ENDPOINT + "/" + URLEncoder.encode("" + id, StandardCharsets.UTF_8).replaceAll("[+]", "%20"), Animal.class);
  }

  public static CompletableFuture<Animal> create(Animal animal) throws JsonProcessingException {
    return Http.post(ENDPOINT, animal, Animal.class);
  }

  public static CompletableFuture<Void> update(long id, Animal animal) throws JsonProcessingException {
    return Http.put(ENDPOINT + "/" + URLEncoder.encode("" + id, StandardCharsets.UTF_8).replaceAll("[+]", "%20"), animal);
  }

  public static CompletableFuture<Void> delete(long id) {
    return Http.delete(ENDPOINT + "/" + URLEncoder.encode("" + id, StandardCharsets.UTF_8).replaceAll("[+]", "%20"));
  }

}
