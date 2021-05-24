package janssen.javafx.controllers;

import javafx.fxml.Initializable;
import javafx.stage.Stage;

public interface IStageController extends Initializable {
  Stage getStage();
  void setStage(Stage stage);
}
