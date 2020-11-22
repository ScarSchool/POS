

import javax.swing.*;
import javafx.application.*;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.util.converter.LongStringConverter;
import javafx.scene.control.*;
import javafx.collections.*;
import java.util.regex.Pattern;
import models.*;
import java.util.*;
import custom.*;

public class Main extends Application {

	JTable table;

	TextField inputMaterial;
	TextField inputOwner;
	CheckBox inputFilled;
	ChoiceBox<String> inputType;

	@Override
	public void start(Stage primaryStage) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			final SwingNode swingNode = new SwingNode();

			SwingUtilities.invokeLater(() -> {
				table = new JTable(new NativeTableModel());
				table.setDefaultRenderer(BottleType.class, new PhoneTypeCellRenderer());
				table.setDefaultEditor(BottleType.class, new PhoneTypeCellEditor());
				JScrollPane pane = new JScrollPane(table);
				swingNode.setContent(pane);
			});
			GridPane grid = new GridPane();
			grid.add(swingNode, 0, 0);
			grid.add(createInputGrid(), 0, 4);
			Button deleteButton = new Button("Delete");
			deleteButton.setOnAction(event -> deleteAction());
			deleteButton.setPrefWidth(800);
			grid.add(deleteButton, 0, 1);
			Button saveButton = new Button("Save");
			saveButton.setOnAction(event->saveAction(primaryStage));
			saveButton.setPrefWidth(800);
			grid.add(saveButton, 0, 2);
			Button loadButton = new Button("load");
			loadButton.setOnAction(event -> loadAction(primaryStage));
			loadButton.setPrefWidth(800);
			grid.add(loadButton, 0, 3);
			primaryStage.setTitle("Bottle manager");
			primaryStage.setScene(new Scene(grid, 800, 800));
			primaryStage.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private GridPane createInputGrid(){
		GridPane inputGrid = new GridPane();
		int colIdx = 0;
		inputMaterial = new TextField();
		TextFormatter<Long> textFormatter = new TextFormatter<>(new LongStringConverter(), 0L, change -> {
			String newText = change.getControlNewText();
			if (Pattern.compile("\\d*").matcher(newText).matches()) {
				return change;
			} else
				return null;
		});

		inputMaterial.setTextFormatter(textFormatter);
		inputGrid.add( new Label("Material:"),0,colIdx++);
		inputGrid.add(inputMaterial, 0, colIdx++);
		inputOwner = new TextField();
		inputGrid.add(new Label("Besitzer:"), 0, colIdx++);
		inputGrid.add(inputOwner, 0, colIdx++);
		inputFilled = new CheckBox("Vollgefüllt");
		inputGrid.add(inputFilled, 0, colIdx++);
		inputGrid.add(new Label("Marke:"), 0, colIdx++);
		inputType = new ChoiceBox<>(FXCollections.observableArrayList(Arrays.stream(BottleType.class.getEnumConstants()).map(Enum::name).toArray(String[]::new)));
		inputGrid.add(inputType, 0, colIdx++);
		Button submitButton = new Button("Speichern");
		submitButton.setOnAction(event->submitAction());
		inputGrid.add(submitButton, 0, colIdx);
		return inputGrid;
	}

	private void submitAction(){
		try{
			if (inputMaterial.getText().isEmpty() || inputOwner.getText().isEmpty()
					|| inputType.getSelectionModel().isEmpty()) {
				throw new Exception("Please fill out all form fields.");
			}
			Bottle newPhoneWhoDis = new Bottle(inputMaterial.getText(), inputOwner.getText(), inputFilled.isSelected(), BottleType.valueOf(inputType.getSelectionModel().getSelectedItem()));
			((NativeTableModel)table.getModel()).addItem(newPhoneWhoDis);
			inputMaterial.clear();
			inputOwner.clear();
			inputFilled.setSelected(false);
			inputType.getSelectionModel().clearSelection();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}

	}

	private void deleteAction(){
		if(table.getSelectedRow() > -1){
			((NativeTableModel)table.getModel()).removeRow(table.getSelectedRow());
		}
	}

	private void loadAction(Stage s){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		String filePath = fileChooser.showOpenDialog(s).getAbsolutePath();
		((NativeTableModel)table.getModel()).loadFromFile(filePath);
	}

	private void saveAction(Stage s){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Data");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"));
		String filePath = fileChooser.showSaveDialog(s).getAbsolutePath();
		((NativeTableModel)table.getModel()).saveToFile(filePath);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
