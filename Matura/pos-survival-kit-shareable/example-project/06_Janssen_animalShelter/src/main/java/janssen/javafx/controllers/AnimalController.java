package janssen.javafx.controllers;

import janssen.javafx.models.Animal;
import janssen.javafx.models.Gender;
import janssen.javafx.services.AnimalService;
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

public class AnimalController implements IModelController {

  @FXML
  protected TableView<Animal> tvAnimals;

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
    tvAnimals.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    tvAnimals.getColumns().add(TableUtils.readonlyColumnFor("id"));

    final TableColumn<Animal, String> tcName = TableUtils.stringColumnFor("name");
    tvAnimals.getColumns().add(tcName);
    tcName.setOnEditCommit(e -> {
      e.consume();
      e.getRowValue().setName(e.getNewValue());
      update(e.getRowValue());
    });

    final TableColumn<Animal, String> tcRace = TableUtils.stringColumnFor("race");
    tvAnimals.getColumns().add(tcRace);
    tcRace.setOnEditCommit(e -> {
      e.consume();
      e.getRowValue().setRace(e.getNewValue());
      update(e.getRowValue());
    });

    final TableColumn<Animal, Boolean> tcSold = TableUtils.booleanColumnFor("sold");
    tvAnimals.getColumns().add(tcSold);
    tcSold.setOnEditCommit(e -> {
      e.consume();
      e.getRowValue().setSold(e.getNewValue());
      update(e.getRowValue());
    });

    refresh(null);
  }

  @FXML
  public void create(ActionEvent event) {
    try {
      Utils.dialog("animals_create");
      refresh(event);
    }
    catch (Exception ex) {
      statusLabel.setText(ex.getMessage());
    }
  }

  public void update(Animal animal) {
    try {
      AnimalService.update(animal.getId(), animal).whenComplete((res, ex) -> {
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
    final Animal animal = tvAnimals.getSelectionModel().getSelectedItem();
    if (animal == null) return;

    try {
      AnimalService.delete(animal.getId()).whenComplete((res, ex) -> {
        if (ex != null) Platform.runLater(() -> statusLabel.setText(ex.getMessage()));
        Platform.runLater(() -> refresh(event));
      });
    }
    catch (Exception ex) {
      statusLabel.setText(ex.getMessage());
    }
  }

  public void refresh(ActionEvent event) {
    AnimalService.listAll().whenComplete((animals, ex) -> {
      if (ex != null) Platform.runLater(() -> statusLabel.setText(ex.getMessage()));
      Platform.runLater(() -> {
        tvAnimals.getItems().clear();
        tvAnimals.getItems().addAll(animals);
        tvAnimals.getSelectionModel().clearSelection();
      });
    });
  }

}
