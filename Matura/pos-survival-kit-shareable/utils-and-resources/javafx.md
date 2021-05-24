## Load a fxml file
Remember, `getResource` searches for the file in the current package. To load a file from a
different package simply use relative paths.
```java
private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
}
```

## Connect a view to a controller
Set the `fx:controller` attribute of the root element to the full classname of the controller, like: 
`fx:controller="janssen.app.Controller"`

## Add an initialize method to a controller
```java
public class Controller implements Initializable {
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /* ... */
    }
}
```

## Access a fxml element
Give the javafx element a `fx:id` attribute with the name of the field in the controller and add the `@FXML` annotation to the field. 
```xml
<!-- view.fxml -->
<Label fx:id="statusLbl" text="..." />
```
```java
// Controller.java
public class Controller implements Initializable {

    @FXML
    Label statusLbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        statusLbl.setText("Yowdiho!");
    }
    
}
```

## Add an event handler to a fxml element
Don't forget the `#` prefix.
```xml
<!-- view.fxml -->
<Label fx:id="statusLbl" />
<Button text="Say hello" onAction="#sayHello" />
```
```java
// Controller.java
public class Controller {

    @FXML
    protected Label statusLbl;

    @FXML
    private void sayHello(ActionEvent event) throws IOException {
        statusLbl.setText( statusLbl.getText() + "Hello! " );
    }
}
```

## JavaFX Properties
Remember to use javafx properies for your models:
```java
  protected IntegerProperty nrOfInventors = new SimpleIntegerProperty();
  protected ObjectProperty<LocalDate> inventedAt = new SimpleObjectProperty<>();
```

## Choicebox vs Combobox
https://stackoverflow.com/a/55485905


## Popup
```java
public class FilePopup {

	public static String show(String filename, String content) {
		Stage popup = new Stage();
		
		popup.setTitle(filename);
		popup.initModality(Modality.APPLICATION_MODAL);
		
		TextArea taContent = new TextArea();
		taContent.setText(content);
		taContent.setPrefWidth(550.0);
		taContent.setPrefHeight(400.0);
		
		Button btnSave = new Button("Save!");
		btnSave.setOnAction((e) -> popup.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(taContent, btnSave);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout, 600, 400);
		popup.setScene(scene);
		popup.showAndWait();
		
		return taContent.getText();
	}
	
}
```
```java

public class InputPopup {

	public static String show(String title, String message) {
		Stage popup = new Stage();
		
		popup.setTitle(title);
		popup.initModality(Modality.APPLICATION_MODAL);
		
		Label lblMessage = new Label(message);
		
		TextField input = new TextField();
		input.setPromptText("Value");
		input.setPrefWidth(200.0);
		input.setOnAction((e) -> popup.close());
		
		Button btnSubmit = new Button("Submit!");
		btnSubmit.setOnAction((e) -> popup.close());
		
		HBox hbox = new HBox(10);
		hbox.getChildren().addAll(input, btnSubmit);
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(lblMessage, hbox);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout, 500, 100);
		popup.setScene(scene);
		popup.show();
		
		return input.getText();
	}
	
}
```
