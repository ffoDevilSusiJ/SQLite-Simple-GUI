package com.sqlitegui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage stage) {
        scene = new Scene(loadFXML("launch"), 900, 600);
        stage.resizableProperty().set(false);
        stage.setScene(scene);
        stage.show();
    }

    private void onLoad()
    {
        
    }

    public static void setRoot(String fxml) {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        
    }

    public static void main(String[] args) {
        launch();
    }
    
    public static Stage getStage() {
        return stage;
    }

}