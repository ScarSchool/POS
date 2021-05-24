package janssen.javafx.controllers;

import janssen.javafx.models.AnimalShelter;
import janssen.javafx.models.Animal;
import janssen.javafx.models.Sales;
import janssen.javafx.services.AnimalShelterService;
import janssen.javafx.services.AnimalService;
import janssen.javafx.services.SalesService;
import janssen.javafx.utils.Utils;
import janssen.javafx.utils.tables.TableUtils;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AnimalShelterController implements IModelController {

  @FXML
  protected TableView<AnimalShelter> tvAnimalShelters;

  @FXML
  protected TabPane tpAssociations;


  protected Stage stage;
  protected Label statusLabel;

  public Stage getStage() {
    return stage;
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  public Label getStatusLabel() {
    return statusLabel;
  }

  public void setStatusLabel(Label statusLabel) {
    this.statusLabel = statusLabel;
  }


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    tvAnimalShelters.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    tvAnimalShelters.getColumns().add(TableUtils.readonlyColumnFor("address"));

    final TableColumn<AnimalShelter, String> tcPhoneNumber = TableUtils.stringColumnFor("phoneNumber");
    tvAnimalShelters.getColumns().add(tcPhoneNumber);
    tcPhoneNumber.setOnEditCommit(e -> {
      e.consume();
      e.getRowValue().setPhoneNumber(e.getNewValue());
      update(e.getRowValue());
    });

    final ListView<Animal> lvAnimals = new ListView<>();
    final Tab tAnimals = new Tab("animals");
    tAnimals.setContent(lvAnimals);
    tpAssociations.getTabs().add(tAnimals);
    Utils.configureListViewAsMultiSelect(lvAnimals);

    AnimalService.listAll().whenComplete((animals, ex) -> {
      if (ex != null) Platform.runLater(() -> statusLabel.setText(ex.getMessage()));
      Platform.runLater(() -> lvAnimals.getItems().addAll(animals));
    });

    lvAnimals.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Animal>) change -> {
      tvAnimalShelters.getSelectionModel().getSelectedItem().setAnimals(lvAnimals.getSelectionModel().getSelectedItems());
      update(tvAnimalShelters.getSelectionModel().getSelectedItem());
    });

    tvAnimalShelters.getSelectionModel().selectedItemProperty().addListener((prop, oldAnimalShelter, newAnimalShelter) -> {
      if (newAnimalShelter == null) return;
      AnimalShelterService.get(newAnimalShelter.getAddress()).whenComplete((animalShelter, ex) -> {
        if (ex != null) Platform.runLater(() -> statusLabel.setText(ex.getMessage()));
        final List<Long> ids = animalShelter.getAnimals().stream().map(Animal::getId).collect(Collectors.toList());
        Platform.runLater(() -> lvAnimals.getItems().stream().filter(animal -> ids.contains(animal.getId())).forEach(lvAnimals.getSelectionModel()::select));
      });
    });

    final ListView<Sales> lvSales = new ListView<>();
    final Tab tSales = new Tab("sales");
    tSales.setContent(lvSales);
    tpAssociations.getTabs().add(tSales);
    Utils.configureListViewAsMultiSelect(lvSales);

    SalesService.listAll().whenComplete((sales, ex) -> {
      if (ex != null) Platform.runLater(() -> statusLabel.setText(ex.getMessage()));
      Platform.runLater(() -> lvSales.getItems().addAll(sales));
    });

    lvSales.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Sales>) change -> {
      tvAnimalShelters.getSelectionModel().getSelectedItem().setSales(lvSales.getSelectionModel().getSelectedItems());
      update(tvAnimalShelters.getSelectionModel().getSelectedItem());
    });

    tvAnimalShelters.getSelectionModel().selectedItemProperty().addListener((prop, oldAnimalShelter, newAnimalShelter) -> {
      if (newAnimalShelter == null) return;
      AnimalShelterService.get(newAnimalShelter.getAddress()).whenComplete((animalShelter, ex) -> {
        if (ex != null) Platform.runLater(() -> statusLabel.setText(ex.getMessage()));
        final List<Long> ids = animalShelter.getSales().stream().map(Sales::getId).collect(Collectors.toList());
        Platform.runLater(() -> lvSales.getItems().stream().filter(sales -> ids.contains(sales.getId())).forEach(lvSales.getSelectionModel()::select));
      });
    });
    refresh(null);
  }

  @FXML
  public void create(ActionEvent event) {
    try {
      Utils.dialog("animalShelters_create");
      refresh(event);
    }
    catch (Exception ex) {
      statusLabel.setText(ex.getMessage());
    }
  }

  public void update(AnimalShelter animalShelter) {
    try {
      AnimalShelterService.update(animalShelter.getAddress(), animalShelter).whenComplete((res, ex) -> {
        if (ex != null) Platform.runLater(() -> statusLabel.setText("Invalid input:\n" + ex.getMessage().replaceFirst(".*?: ", "")));
        else Platform.runLater(() -> refresh(null));
      });
    }
    catch (Exception ex) {
      statusLabel.setText(ex.getMessage());
    }
  }

  @FXML
  public void delete(ActionEvent event) {
    final AnimalShelter animalShelter = tvAnimalShelters.getSelectionModel().getSelectedItem();
    if (animalShelter == null) return;

    try {
      AnimalShelterService.delete(animalShelter.getAddress()).whenComplete((res, ex) -> {
        if (ex != null) Platform.runLater(() -> statusLabel.setText(ex.getMessage()));
        Platform.runLater(() -> refresh(event));
      });
    }
    catch (Exception ex) {
      statusLabel.setText(ex.getMessage());
    }
  }

  public void refresh(ActionEvent event) {
    AnimalShelterService.listAll().whenComplete((animalShelters, ex) -> {
      if (ex != null) Platform.runLater(() -> statusLabel.setText(ex.getMessage()));
      Platform.runLater(() -> {
        tvAnimalShelters.getItems().clear();
        tvAnimalShelters.getItems().addAll(animalShelters);
        tvAnimalShelters.getSelectionModel().clearSelection();
      });
    });
  }

}
