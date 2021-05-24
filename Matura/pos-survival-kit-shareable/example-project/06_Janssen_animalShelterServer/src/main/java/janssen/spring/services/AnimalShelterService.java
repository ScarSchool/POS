package janssen.spring.services;

import janssen.spring.entities.AnimalShelter;
import janssen.spring.repositories.AnimalShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
public class AnimalShelterService {

  protected final AnimalShelterRepository data;

  public AnimalShelterService(@Autowired AnimalShelterRepository data) {
    this.data = data;
  }

  public Collection<AnimalShelter> listAll() {
    return data.findAll();
  }

  public AnimalShelter create(AnimalShelter animalShelter) {
    verifyAnimalShelterIsUnique(animalShelter);
    animalShelter = data.saveAndFlush(animalShelter);
    return animalShelter;
  }

  public AnimalShelter get(String address) {
    verifyAnimalShelterExists(address);
    return data.findById(address).get();
  }

  public AnimalShelter update(String address, AnimalShelter animalShelter) {
    animalShelter.setAddress(address);
    verifyAnimalShelterExists(address);
    return data.saveAndFlush(animalShelter);
  }

  public void delete(String address) {
    try { data.deleteById(address); }
    catch (EmptyResultDataAccessException ignored) {}
  }

  protected void verifyAnimalShelterExists(String address) {
    if (!data.existsById(address))
      throw new NoSuchElementException("AnimalShelter with address '" + address + "' does not exist");
  }

  protected void verifyAnimalShelterIsUnique(AnimalShelter animalShelter) {
    if (data.existsById(animalShelter.getAddress())) {
      throw new DuplicateKeyException("AnimalShelter with address '" + animalShelter.getAddress() + "' already exists");
    }
  }

}
