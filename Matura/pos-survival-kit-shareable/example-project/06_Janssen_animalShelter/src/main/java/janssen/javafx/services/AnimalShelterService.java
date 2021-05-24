package janssen.javafx.services;

import janssen.javafx.models.AnimalShelter;
import janssen.javafx.utils.Http;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AnimalShelterService {

  public static final String ENDPOINT = "/shelters";

  public static CompletableFuture<List<AnimalShelter>> listAll() {
    return Http.getCollection(ENDPOINT, AnimalShelter.class);
  }

  public static CompletableFuture<AnimalShelter> get(String address) {
    return Http.get(ENDPOINT + "/" + URLEncoder.encode("" + address, StandardCharsets.UTF_8).replaceAll("[+]", "%20"), AnimalShelter.class);
  }

  public static CompletableFuture<AnimalShelter> create(AnimalShelter animalShelter) throws JsonProcessingException {
    return Http.post(ENDPOINT, animalShelter, AnimalShelter.class);
  }

  public static CompletableFuture<Void> update(String address, AnimalShelter animalShelter) throws JsonProcessingException {
    return Http.put(ENDPOINT + "/" + URLEncoder.encode("" + address, StandardCharsets.UTF_8).replaceAll("[+]", "%20"), animalShelter);
  }

  public static CompletableFuture<Void> delete(String address) {
    return Http.delete(ENDPOINT + "/" + URLEncoder.encode("" + address, StandardCharsets.UTF_8).replaceAll("[+]", "%20"));
  }

}
