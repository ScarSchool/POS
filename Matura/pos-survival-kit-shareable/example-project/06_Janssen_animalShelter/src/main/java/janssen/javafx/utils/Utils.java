package janssen.javafx.utils;

import janssen.javafx.App;
import janssen.javafx.controllers.IStageController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class Utils {

  public static String[] getEnumValues(Class<? extends Enum<?>> enumClass) {
    return Arrays.stream(enumClass.getEnumConstants()).map(Enum::name).toArray(String[]::new);
  }

  public static String camelCaseToTitleCase(String camelcase) {
    final String regex = "(?<=[A-Z])(?=[A-Z][a-z])|(?<=[^A-Z])(?=[A-Z])|(?<=[A-Za-z])(?=[^A-Za-z])";
    return Character.toUpperCase(camelcase.charAt(0)) + camelcase.substring(1).replaceAll(regex, " ");
  }

  public static String titleCaseToCamelCase(String titlecase) {
    return Character.toLowerCase(titlecase.charAt(0)) + titlecase.substring(1).replaceAll(" ", "");
  }

  public static void dialog(String viewName) throws IOException {
    final Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);

    final FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/" + viewName + ".fxml"));
    final Parent root = fxmlLoader.load();
    ((IStageController) fxmlLoader.getController()).setStage(dialog);

    final Scene scene = new Scene(root, 540, 720);
    dialog.setScene(scene);
    dialog.showAndWait();
  }

  public static <T> void configureListViewAsMultiSelect(ListView<T> listView) {
    listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    listView.setCellFactory(lv -> {
      ListCell<T> cell = new ListCell<>() {
        @Override
        protected void updateItem(T item, boolean empty) {
          super.updateItem(item, empty);
          if (!empty) setText(item.toString());
        }
      };

      cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
        event.consume();
        lv.requestFocus();
        if (cell.isEmpty()) return;

        final MultipleSelectionModel<T> selection = lv.getSelectionModel();
        final int cellIndex = cell.getIndex();
        if (selection.getSelectedIndices().contains(cellIndex)) selection.clearSelection(cellIndex);
        else selection.select(cellIndex);
      });

      return cell;
    });
  }

}
