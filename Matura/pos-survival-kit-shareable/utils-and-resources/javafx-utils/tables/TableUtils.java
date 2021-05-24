package WRONG_PACKAGE.utils.tables;

import WRONG_PACKAGE.utils.Utils;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.*;
import javafx.util.converter.*;

import java.time.LocalDate;

public class TableUtils {
  
  public static <S, T> TableColumn<S, T> readonlyColumnFor(String propertyName) {
    TableColumn<S, T> tableColumn = new TableColumn<>(Utils.camelCaseToTitleCase(propertyName));
    tableColumn.setCellValueFactory(new PropertyValueFactory<>(propertyName));
    return tableColumn;
  }

  public static <S> TableColumn<S, String> stringColumnFor(String propertyName) {
    TableColumn<S, String> tableColumn = readonlyColumnFor(propertyName);
    tableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    tableColumn.setEditable(true);
    return tableColumn;
  }

  public static <S> TableColumn<S, String> multiLineStringColumnFor(String propertyName) {
    TableColumn<S, String> tableColumn = readonlyColumnFor(propertyName);
    tableColumn.setCellFactory(TextAreaTableCell.forTableColumn());
    tableColumn.setEditable(true);
    return tableColumn;
  }

  public static <S> TableColumn<S, Integer> integerColumnFor(String propertyName) {
    TableColumn<S, Integer> tableColumn = readonlyColumnFor(propertyName);
    tableColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    tableColumn.setEditable(true);
    return tableColumn;
  }

  public static <S> TableColumn<S, Long> longColumnFor(String propertyName) {
    TableColumn<S, Long> tableColumn = readonlyColumnFor(propertyName);
    tableColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
    tableColumn.setEditable(true);
    return tableColumn;
  }

  public static <S> TableColumn<S, Float> floatColumnFor(String propertyName) {
    TableColumn<S, Float> tableColumn = readonlyColumnFor(propertyName);
    tableColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
    tableColumn.setEditable(true);
    return tableColumn;
  }

  public static <S> TableColumn<S, Double> doubleColumnFor(String propertyName) {
    TableColumn<S, Double> tableColumn = readonlyColumnFor(propertyName);
    tableColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
    tableColumn.setEditable(true);
    return tableColumn;
  }

  public static <S> TableColumn<S, Boolean> booleanColumnFor(String propertyName) {
    TableColumn<S, Boolean> tableColumn = readonlyColumnFor(propertyName);
    tableColumn.setCellFactory(CheckBoxTableCell.forTableColumn(tableColumn));
    tableColumn.setEditable(true);
    return tableColumn;
  }

  public static <S> TableColumn<S, LocalDate> dateColumnFor(String propertyName) {
    TableColumn<S, LocalDate> tableColumn = readonlyColumnFor(propertyName);
    tableColumn.setCellFactory(DatePickerTableCell.forTableColumn());
    tableColumn.setEditable(true);
    return tableColumn;
  }

  public static <S, T> TableColumn<S, T> choiceBoxColumnFor(String propertyName, T[] choices) {
    TableColumn<S, T> tableColumn = readonlyColumnFor(propertyName);
    tableColumn.setCellFactory((tc) -> new ChoiceBoxTableCell<>(choices));
    tableColumn.setEditable(true);
    return tableColumn;
  }

  public static <S, T extends Enum<T>> TableColumn<S, T> enumColumnFor(String propertyName, Class<T> columnType) {
    TableColumn<S, T> tableColumn = readonlyColumnFor(propertyName);
    tableColumn.setCellFactory((tc) -> new ChoiceBoxTableCell<>(columnType.getEnumConstants()));
    tableColumn.setEditable(true);
    return tableColumn;
  }

}
