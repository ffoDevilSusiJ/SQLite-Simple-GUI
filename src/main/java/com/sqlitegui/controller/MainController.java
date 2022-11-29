package com.sqlitegui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sqlitegui.model.AppHandler;
import com.sqlitegui.model.data.Column;
import com.sqlitegui.model.data.DataBaseHandler;
import com.sqlitegui.model.data.Table;
import com.sqlitegui.view.SqlTableCell;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class MainController implements Initializable {

    private static SqlTableCell editCell;

    @FXML
    TableView tableView;

    @FXML
    VBox tableList;

    Table currenTable;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        AppHandler.load();
  
        tableView.setEditable(true);
        new Thread(() -> {
            while (AppHandler.getTables() == null) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            Platform.runLater(() -> {
                initTableList();
            });
        }).start();

    }

    private void initTableView() {
        tableView.getColumns().clear();
        tableView.getItems().clear();
        
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        Column firstColumn = currenTable.getColumns().get(0);
        for (int i = 0; i < currenTable.getColumns().size(); i++) {
            int finalI = i;
            
            Column column = currenTable.getColumns().get(i);
            TableColumn<ObservableList<String>, String> tableColumn = new TableColumn<>(column.getName());
            
            tableColumn.setCellFactory((param) -> new SqlTableCell(column));
            tableColumn.setOnEditCommit((e) -> {
                
                System.out.println(column.getValues().get(firstColumn.getValues().get(e.getRowValue())));
            });
            
            
            tableColumn.setCellValueFactory(param -> {
                return new SimpleStringProperty(param.getValue().get(finalI));
            });
            tableView.getColumns().add(tableColumn);
        }

            ArrayList<String>[] rows = currenTable.formateRow();
            for (int j = 0; j < currenTable.getRowCount(); j++) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.addAll(rows[j]);
                data.add(row);
                
            }
            
            tableView.setItems(data);
            
    }

    private void initTableList() {
        ArrayList<Table> tables = AppHandler.getTables();
        for (Table table : tables) {
            Label tableLink = new Label(table.getName());
            tableLink.setOnMouseClicked((e) -> {
                currenTable = table;
                initTableView();
            });
            tableList.getChildren().add(tableLink);
        }
    }

    @FXML
    private void saveDataBase()
    {
        AppHandler.getdHandler().saveAllChanges(AppHandler.getTables());

    }

    public static SqlTableCell getEditCell() {
        return editCell;
    }
    public static void setEditCell(SqlTableCell editCell) {
        MainController.editCell = editCell;
    }
}
