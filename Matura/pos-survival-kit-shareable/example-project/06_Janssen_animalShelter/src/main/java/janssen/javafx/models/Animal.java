package janssen.javafx.models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Animal {
  
  protected LongProperty id = new SimpleLongProperty();
  protected StringProperty name = new SimpleStringProperty();
  protected StringProperty race = new SimpleStringProperty();
  protected ObjectProperty<LocalDate> dateOfBirth = new SimpleObjectProperty<>();
  protected StringProperty note = new SimpleStringProperty();
  protected ObjectProperty<Gender> gender = new SimpleObjectProperty<>(Gender.Other);
  protected BooleanProperty sold = new SimpleBooleanProperty(false);
  
  public long getId() {
    return id.get();
  }
  
  public LongProperty idProperty() {
    return id;
  }
  
  public void setId(long id) {
    this.id.set(id);
  }
  
  public String getName() {
    return name.get();
  }
  
  public StringProperty nameProperty() {
    return name;
  }
  
  public void setName(String name) {
    this.name.set(name);
  }
  
  public String getRace() {
    return race.get();
  }
  
  public StringProperty raceProperty() {
    return race;
  }
  
  public void setRace(String race) {
    this.race.set(race);
  }
  
  public LocalDate getDateOfBirth() {
    return dateOfBirth.get();
  }
  
  public ObjectProperty<LocalDate> dateOfBirthProperty() {
    return dateOfBirth;
  }
  
  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth.set(dateOfBirth);
  }
  
  public String getNote() {
    return note.get();
  }
  
  public StringProperty noteProperty() {
    return note;
  }
  
  public void setNote(String note) {
    this.note.set(note);
  }
  
  public Gender getGender() {
    return gender.get();
  }
  
  public ObjectProperty<Gender> genderProperty() {
    return gender;
  }
  
  public void setGender(Gender gender) {
    this.gender.set(gender);
  }
  
  public boolean isSold() {
    return sold.get();
  }
  
  public BooleanProperty soldProperty() {
    return sold;
  }
  
  public void setSold(boolean sold) {
    this.sold.set(sold);
  }
  
  @Override
  public String toString() {
    return "Animal(id=" + getId() + ")";
  }
  
}
