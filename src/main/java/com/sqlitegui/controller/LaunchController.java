package com.sqlitegui.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sqlitegui.App;
import com.sqlitegui.model.AppHandler;
import com.sqlitegui.model.TempFilesHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class LaunchController implements Initializable {
    @FXML
    public VBox recentFilesVBox;

    @FXML
    private void newFileClick() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SQLite files (.sqlite)", "*.sqlite"));
        File file = fileChooser.showSaveDialog(App.getStage());
        file.createNewFile();
        if (file == null)
            return;
        AppHandler.setFile(file);
        TempFilesHandler.addToRecentFile(file.getAbsolutePath());
        App.setRoot("main");

    }

    @FXML
    private void openFileClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("DataBase files", "*.db"));
        File file = fileChooser.showOpenDialog(App.getStage());
        if (file == null)
            return;
        AppHandler.setFile(file);
        TempFilesHandler.addToRecentFile(file.getAbsolutePath());
        App.setRoot("main");
    }

    @FXML
    private void selectFileClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("DataBase files", "*.db"));
        File file = fileChooser.showOpenDialog(App.getStage());
        if (file == null)
            return;
        AppHandler.setFile(file);
        TempFilesHandler.addToRecentFile(file.getAbsolutePath());
        App.setRoot("main");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Label recentLabel = null;
        ArrayList<String> recentList = (ArrayList<String>) TempFilesHandler.getRecentFiles();
        for (int i = 0; i < recentList.size(); i++) {
            recentLabel = new Label(recentList.get(i));
            recentLabel.setId("recent");
            int finalI = i;
            recentLabel.setOnMouseClicked((e) -> {
                File file = new File(recentList.get(finalI));
                AppHandler.setFile(file);
                TempFilesHandler.addToRecentFile(file.getAbsolutePath());
                App.setRoot("main");
            });
            recentFilesVBox.getChildren().add(recentLabel);

        }

    }

}
