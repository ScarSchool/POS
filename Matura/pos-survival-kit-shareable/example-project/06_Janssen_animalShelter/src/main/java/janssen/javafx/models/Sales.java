package janssen.javafx.models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Sales {
  
  protected LongProperty id = new SimpleLongProperty();
  protected ObjectProperty<AnimalShelter> shelter = new SimpleObjectProperty<>();
  protected ObjectProperty<Animal> animal = new SimpleObjectProperty<>();
  protected DoubleProperty price = new SimpleDoubleProperty();
  protected ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
  
  public long getId() {
    return id.get();
  }
  
  public LongProperty idProperty() {
    return id;
  }
  
  public void setId(long id) {
    this.id.set(id);
  }
  
  public AnimalShelter getShelter() {
    return shelter.get();
  }
  
  public ObjectProperty<AnimalShelter> shelterProperty() {
    return shelter;
  }
  
  public void setShelter(AnimalShelter shelter) {
    this.shelter.set(shelter);
  }
  
  public Animal getAnimal() {
    return animal.get();
  }
  
  public ObjectProperty<Animal> animalProperty() {
    return animal;
  }
  
  public void setAnimal(Animal animal) {
    this.animal.set(animal);
  }
  
  public double getPrice() {
    return price.get();
  }
  
  public DoubleProperty priceProperty() {
    return price;
  }
  
  public void setPrice(double price) {
    this.price.set(price);
  }
  
  public LocalDate getDate() {
    return date.get();
  }
  
  public ObjectProperty<LocalDate> dateProperty() {
    return date;
  }
  
  public void setDate(LocalDate date) {
    this.date.set(date);
  }
  
  @Override
  public String toString() {
    return "Sales(id=" + getId() + ")";
  }
  
}
