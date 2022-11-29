package com.sqlitegui.view;

import com.sqlitegui.controller.MainController;
import com.sqlitegui.model.data.Column;

import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class SqlTableCell<S,T> extends TableCell<ObservableList<String>, String>{

    Column column;
    TextField field;
    String oldValue;
    String newValue;

    public SqlTableCell(Column column){
        this.column = column;
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {    
            setText(item);            
        }
    }
    
    @Override
    public void startEdit() {
        super.startEdit();

        oldValue = getText();
        if(MainController.getEditCell() == null) MainController.setEditCell(this);
            else {
                MainController.getEditCell().cancelEdit();
                MainController.setEditCell(this);
            }
            
            field = new TextField(getText());
            setText(null);
            setGraphic(field);
            field.textProperty().addListener((observable, oldValue, newValue) -> {
                this.newValue = newValue;
            });
            field.setOnKeyPressed((e) -> {
                if (e.getCode() == KeyCode.ENTER) {
                    commitEdit(newValue);
                }
            });
            
        
    }

    @Override
    public void commitEdit(String newValue) {
        super.commitEdit(newValue);
        column.getValues().remove(String.valueOf(getIndex() + 1));
        column.getValues().put(String.valueOf(getIndex() + 1), newValue);
        setGraphic(null);
        setText(newValue);
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setGraphic(null);
        setText(oldValue);
        
    }
    // private javafx.beans.property.ObjectProperty<javafx.event.EventHandler<javafx.scene.control.TableColumn$CellEditEvent<S,T>>> onEditStart()
    // {
        
    // }
}
