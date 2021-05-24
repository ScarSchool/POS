package janssen.javafx.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public interface IModelController extends IStageController {
  Label getStatusLabel();
  void setStatusLabel(Label statusLabel);

  void create(ActionEvent event);
  void delete(ActionEvent event);
  void refresh(ActionEvent event);
}
