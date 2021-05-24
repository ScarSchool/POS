package janssen.javafx.controllers;

import janssen.javafx.models.AnimalShelter;
import janssen.javafx.services.AnimalShelterService;
import janssen.javafx.models.Animal;
import janssen.javafx.models.Sales;
import janssen.javafx.services.AnimalService;
import janssen.javafx.services.SalesService;
import janssen.javafx.utils.Utils;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AnimalShelterCreateController implements IStageController {

  @FXML
  protected TextField tfAddress;

  @FXML
  protected ListView<Animal> lvAnimals;

  @FXML
  protected TextField tfPhoneNumber;

  @FXML
  protected ListView<Sales> lvSales;
  
  @FXML
  protected Label lblErrors;


  protected Stage stage;

  public Stage getStage() {
    return stage;
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }


  public final AnimalShelter animalShelter = new AnimalShelter();

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    Utils.configureListViewAsMultiSelect(lvAnimals);
    AnimalService.listAll().whenComplete((animals, ex) -> {
      if (ex != null) Platform.runLater(() -> lblErrors.setText(ex.getMessage()));
      Platform.runLater(() -> lvAnimals.getItems().addAll(animals));
    });

    Utils.configureListViewAsMultiSelect(lvSales);
    SalesService.listAll().whenComplete((sales, ex) -> {
      if (ex != null) Platform.runLater(() -> lblErrors.setText(ex.getMessage()));
      Platform.runLater(() -> lvSales.getItems().addAll(sales));
    });

    tfAddress.textProperty().bindBidirectional(animalShelter.addressProperty());
    lvAnimals.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Animal>) change -> animalShelter.setAnimals(lvAnimals.getSelectionModel().getSelectedItems()));
    tfPhoneNumber.textProperty().bindBidirectional(animalShelter.phoneNumberProperty());
    lvSales.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Sales>) change -> animalShelter.setSales(lvSales.getSelectionModel().getSelectedItems()));
  }

  @FXML
  public void create(ActionEvent event) {
    try {
      AnimalShelterService.create(animalShelter).whenComplete((res, ex) -> {
        if (ex != null) Platform.runLater(() -> lblErrors.setText("Invalid input:\n" + ex.getMessage().replaceFirst(".*?: ", "")));
        else Platform.runLater(stage::close);
      });
    }
    catch (Exception ex) {
      lblErrors.setText(ex.getMessage());
    }
  }

}
