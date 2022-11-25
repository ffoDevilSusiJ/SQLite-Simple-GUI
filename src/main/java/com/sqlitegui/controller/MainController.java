package com.sqlitegui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.sqlitegui.model.AppHandler;

import javafx.fxml.Initializable;

public class MainController implements Initializable{

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        AppHandler.load();
    }

    
    
}
