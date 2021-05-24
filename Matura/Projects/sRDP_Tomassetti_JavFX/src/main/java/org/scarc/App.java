package org.scarc;

import com.google.gson.Gson;
import com.squareup.okhttp.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.scarc.data.object;

import java.awt.*;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    private static OkHttpClient client = new OkHttpClient();

    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();

        // Schau da des do on https://square.github.io/okhttp/
        Request request = new Request.Builder()
                .url("url")
                .build();

        try  {
            Response response = client.newCall(request).execute();
            System.out.println((response.body().string()));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}