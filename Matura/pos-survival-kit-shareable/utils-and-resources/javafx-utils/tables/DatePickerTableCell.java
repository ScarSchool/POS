package WRONG_PACKAGE.utils.tables;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.time.LocalDate;

public class DatePickerTableCell<S> extends TableCell<S, LocalDate> {

  protected DatePicker datePicker;

  public static <S> Callback<TableColumn<S, LocalDate>, TableCell<S, LocalDate>> forTableColumn() {
    return tc -> new DatePickerTableCell<>();
  }

  @Override
  protected void updateItem(LocalDate item, boolean empty) {
    super.updateItem(item, empty);

    if (empty) {
      setText(null);
      setGraphic(null);
      return;
    }

    if (isEditing()) displayDatePicker();
    else displayDateText();
  }

  @Override
  public void startEdit() {
    super.startEdit();
    displayDatePicker();
  }

  @Override
  public void cancelEdit() {
    super.cancelEdit();
    displayDateText();
  }

  protected void displayDateText() {
    setText(getItem().toString());
    setGraphic(null);
  }

  protected void displayDatePicker() {
    datePicker = new DatePicker(getItem());
    datePicker.setOnAction(e -> commitEdit(datePicker.getValue()));
    setGraphic(datePicker);
    setText(null);
  }
}
