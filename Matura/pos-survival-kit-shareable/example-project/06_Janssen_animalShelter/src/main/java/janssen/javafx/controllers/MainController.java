package janssen.javafx.controllers;

import janssen.javafx.App;
import janssen.javafx.utils.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements IStageController {

  @FXML
  protected TabPane tpContent;

  @FXML
  protected Label lblStatus;


  public Stage stage;

  public Stage getStage() {
    return stage;
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }


  protected IModelController controller;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    tpContent.getTabs().addAll(new Tab("Sales"), new Tab("Animal Shelters"), new Tab("Animals"));

    tpContent.getSelectionModel().selectedItemProperty().addListener((prop, oldTab, newTab) -> loadTab(newTab));
    loadTab(tpContent.getSelectionModel().getSelectedItem());
  }

  @FXML
  public void create(ActionEvent event) {
    controller.create(event);
  }

  @FXML
  public void delete(ActionEvent event) {
    controller.delete(event);
  }

  @FXML
  public void refresh(ActionEvent event) {
    controller.refresh(event);
  }

  @FXML
  public void close(ActionEvent event) {
    stage.close();
  }

  protected void loadTab(Tab tab) {
    try {
      final FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/" + Utils.titleCaseToCamelCase(tab.getText()) + ".fxml"));
      final Parent root = fxmlLoader.load();
      tab.setContent(root);
      controller = fxmlLoader.getController();
      controller.setStage(stage);
      controller.setStatusLabel(lblStatus);
    }
    catch (Exception ex) {
      lblStatus.setText(ex.getMessage());
    }
  }

}
