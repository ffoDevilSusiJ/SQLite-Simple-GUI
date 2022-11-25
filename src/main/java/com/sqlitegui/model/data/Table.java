package com.sqlitegui.model.data;

import java.util.ArrayList;

public class Table {
    private String name;
    private ArrayList<Column> columns;

    public Table(String name) {
        this.name = name;
        columns = new ArrayList<>();
    }

    public void addColumn(Column column) {
        columns.add(column);
    }

    public ArrayList<Column> getColumns() {
        return columns;
    }
}
