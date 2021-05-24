package janssen.javafx.controllers;

import janssen.javafx.models.Sales;
import janssen.javafx.models.AnimalShelter;
import janssen.javafx.models.Animal;
import janssen.javafx.services.SalesService;
import janssen.javafx.services.AnimalShelterService;
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

public class SalesController implements IModelController {

  @FXML
  protected TableView<Sales> tvSales;

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
    tvSales.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    tvSales.getColumns().add(TableUtils.readonlyColumnFor("id"));

    AnimalShelterService.listAll().whenComplete((animalShelters, ex) -> {
      if (ex != null) Platform.runLater(() -> statusLabel.setText(ex.getMessage()));
      Platform.runLater(() -> {
        final TableColumn<Sales, AnimalShelter> tcShelter = TableUtils.choiceBoxColumnFor("shelter", animalShelters.toArray(new AnimalShelter[0]));
        tcShelter.setOnEditCommit(e -> update(e.getRowValue()));
        tvSales.getColumns().add(tcShelter);
      });
    });

    AnimalService.listAll().whenComplete((animals, ex) -> {
      if (ex != null) Platform.runLater(() -> statusLabel.setText(ex.getMessage()));
      Platform.runLater(() -> {
        final TableColumn<Sales, Animal> tcAnimal = TableUtils.choiceBoxColumnFor("animal", animals.toArray(new Animal[0]));
        tcAnimal.setOnEditCommit(e -> update(e.getRowValue()));
        tvSales.getColumns().add(tcAnimal);
      });
    });

    final TableColumn<Sales, Double> tcPrice = TableUtils.doubleColumnFor("price");
    tvSales.getColumns().add(tcPrice);
    tcPrice.setOnEditCommit(e -> {
      e.consume();
      e.getRowValue().setPrice(e.getNewValue());
      update(e.getRowValue());
    });

    final TableColumn<Sales, LocalDate> tcDate = TableUtils.dateColumnFor("date");
    tvSales.getColumns().add(tcDate);
    tcDate.setOnEditCommit(e -> {
      e.consume();
      e.getRowValue().setDate(e.getNewValue());
      update(e.getRowValue());
    });

    refresh(null);
  }

  @FXML
  public void create(ActionEvent event) {
    try {
      Utils.dialog("sales_create");
      refresh(event);
    }
    catch (Exception ex) {
      statusLabel.setText(ex.getMessage());
    }
  }

  public void update(Sales sales) {
    try {
      SalesService.update(sales.getId(), sales).whenComplete((res, ex) -> {
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
    final Sales sales = tvSales.getSelectionModel().getSelectedItem();
    if (sales == null) return;

    try {
      SalesService.delete(sales.getId()).whenComplete((res, ex) -> {
        if (ex != null) Platform.runLater(() -> statusLabel.setText(ex.getMessage()));
        Platform.runLater(() -> refresh(event));
      });
    }
    catch (Exception ex) {
      statusLabel.setText(ex.getMessage());
    }
  }

  public void refresh(ActionEvent event) {
    SalesService.listAll().whenComplete((sales, ex) -> {
      if (ex != null) Platform.runLater(() -> statusLabel.setText(ex.getMessage()));
      Platform.runLater(() -> {
        tvSales.getItems().clear();
        tvSales.getItems().addAll(sales);
        tvSales.getSelectionModel().clearSelection();
      });
    });
  }

}
