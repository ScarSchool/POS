package WRONG_PACKAGE.utils.tables;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.util.Callback;

public class TextAreaTableCell<S> extends TableCell<S, String> {

  protected TextArea textArea;

  public static <S> Callback<TableColumn<S, String>, TableCell<S, String>> forTableColumn() {
    return tc -> new TextAreaTableCell<>();
  }

  @Override
  protected void updateItem(String item, boolean empty) {
    super.updateItem(item, empty);

    if (empty) {
      setText(null);
      setGraphic(null);
      return;
    }

    if (isEditing()) displayTextArea();
    else displayText();
  }

  @Override
  public void startEdit() {
    super.startEdit();
    displayTextArea();
  }

  @Override
  public void cancelEdit() {
    super.cancelEdit();
    displayText();
  }

  protected void displayText() {
    setText(getItem());
    setGraphic(null);
  }

  protected void displayTextArea() {
    textArea = new TextArea(getItem());
    textArea.setOnKeyPressed(e -> {
      if (e.isControlDown() && e.getCode() == KeyCode.ENTER) commitEdit(textArea.getText());
    });
    setGraphic(textArea);
    setText(null);
  }
}
