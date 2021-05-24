package janssen.javafx.controllers;

import janssen.javafx.models.Animal;
import janssen.javafx.services.AnimalService;
import janssen.javafx.models.Gender;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AnimalCreateController implements IStageController {

  @FXML
  protected TextField tfName;

  @FXML
  protected TextField tfRace;

  @FXML
  protected DatePicker dpDateOfBirth;

  @FXML
  protected TextArea taNote;

  @FXML
  protected ChoiceBox<Gender> cbGender;

  @FXML
  protected CheckBox cbSold;
  
  @FXML
  protected Label lblErrors;


  protected Stage stage;

  public Stage getStage() {
    return stage;
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }


  public final Animal animal = new Animal();

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    cbGender.getItems().addAll(Gender.values());

    tfName.textProperty().bindBidirectional(animal.nameProperty());
    tfRace.textProperty().bindBidirectional(animal.raceProperty());
    dpDateOfBirth.valueProperty().bindBidirectional(animal.dateOfBirthProperty());
    taNote.textProperty().bindBidirectional(animal.noteProperty());
    cbGender.valueProperty().bindBidirectional(animal.genderProperty());
    cbSold.selectedProperty().bindBidirectional(animal.soldProperty());
  }

  @FXML
  public void create(ActionEvent event) {
    try {
      AnimalService.create(animal).whenComplete((res, ex) -> {
        if (ex != null) Platform.runLater(() -> lblErrors.setText("Invalid input:\n" + ex.getMessage().replaceFirst(".*?: ", "")));
        else Platform.runLater(stage::close);
      });
    }
    catch (Exception ex) {
      lblErrors.setText(ex.getMessage());
    }
  }

}
