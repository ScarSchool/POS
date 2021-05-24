package janssen.spring.services;

import janssen.spring.entities.Animal;
import janssen.spring.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
public class AnimalService {

  protected final AnimalRepository data;

  public AnimalService(@Autowired AnimalRepository data) {
    this.data = data;
  }

  public Collection<Animal> listAll() {
    return data.findAll();
  }

  public Animal create(Animal animal) {
    verifyAnimalIsUnique(animal);
    animal = data.saveAndFlush(animal);
    return animal;
  }

  public Animal get(long id) {
    verifyAnimalExists(id);
    return data.findById(id).get();
  }

  public Animal update(long id, Animal animal) {
    animal.setId(id);
    verifyAnimalExists(id);
    return data.saveAndFlush(animal);
  }

  public void delete(long id) {
    try { data.deleteById(id); }
    catch (EmptyResultDataAccessException ignored) {}
  }

  protected void verifyAnimalExists(long id) {
    if (!data.existsById(id))
      throw new NoSuchElementException("Animal with id '" + id + "' does not exist");
  }

  protected void verifyAnimalIsUnique(Animal animal) {
    if (data.existsById(animal.getId())) {
      throw new DuplicateKeyException("Animal with id '" + animal.getId() + "' already exists");
    }
  }

}
