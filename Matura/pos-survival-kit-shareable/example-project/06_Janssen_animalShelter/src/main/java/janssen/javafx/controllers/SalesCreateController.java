package janssen.javafx.controllers;

import janssen.javafx.models.Sales;
import janssen.javafx.services.SalesService;
import janssen.javafx.models.AnimalShelter;
import janssen.javafx.models.Animal;
import janssen.javafx.services.AnimalShelterService;
import janssen.javafx.services.AnimalService;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class SalesCreateController implements IStageController {

  @FXML
  protected ChoiceBox<AnimalShelter> cbShelter;

  @FXML
  protected ChoiceBox<Animal> cbAnimal;

  @FXML
  protected TextField tfPrice;

  @FXML
  protected DatePicker dpDate;
  
  @FXML
  protected Label lblErrors;


  protected Stage stage;

  public Stage getStage() {
    return stage;
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }


  public final Sales sales = new Sales();

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    AnimalShelterService.listAll().whenComplete((animalShelters, ex) -> {
      if (ex != null) Platform.runLater(() -> lblErrors.setText(ex.getMessage()));
      Platform.runLater(() -> cbShelter.getItems().addAll(animalShelters));
    });

    AnimalService.listAll().whenComplete((animals, ex) -> {
      if (ex != null) Platform.runLater(() -> lblErrors.setText(ex.getMessage()));
      Platform.runLater(() -> cbAnimal.getItems().addAll(animals));
    });

    cbShelter.valueProperty().bindBidirectional(sales.shelterProperty());
    cbAnimal.valueProperty().bindBidirectional(sales.animalProperty());
    tfPrice.textProperty().bindBidirectional(sales.priceProperty(), new NumberStringConverter());
    dpDate.valueProperty().bindBidirectional(sales.dateProperty());
  }

  @FXML
  public void create(ActionEvent event) {
    try {
      SalesService.create(sales).whenComplete((res, ex) -> {
        if (ex != null) Platform.runLater(() -> lblErrors.setText("Invalid input:\n" + ex.getMessage().replaceFirst(".*?: ", "")));
        else Platform.runLater(stage::close);
      });
    }
    catch (Exception ex) {
      lblErrors.setText(ex.getMessage());
    }
  }

}
