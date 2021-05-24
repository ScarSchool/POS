package janssen.javafx.models;

import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

public class AnimalShelter {
  
  protected StringProperty address = new SimpleStringProperty();
  protected List<Animal> animals = new ArrayList<>();
  protected StringProperty phoneNumber = new SimpleStringProperty();
  protected List<Sales> sales = new ArrayList<>();
  
  public String getAddress() {
    return address.get();
  }
  
  public StringProperty addressProperty() {
    return address;
  }
  
  public void setAddress(String address) {
    this.address.set(address);
  }
  
  public List<Animal> getAnimals() {
    return animals;
  }
  
  public void setAnimals(List<Animal> animals) {
    this.animals = animals;
  }
  
  public String getPhoneNumber() {
    return phoneNumber.get();
  }
  
  public StringProperty phoneNumberProperty() {
    return phoneNumber;
  }
  
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber.set(phoneNumber);
  }
  
  public List<Sales> getSales() {
    return sales;
  }
  
  public void setSales(List<Sales> sales) {
    this.sales = sales;
  }
  
  @Override
  public String toString() {
    return "AnimalShelter(address=" + getAddress() + ")";
  }
  
}
