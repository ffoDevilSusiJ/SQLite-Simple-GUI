package com.sqlitegui.model.data;

import java.util.ArrayList;

public class Table {
    private String name;
    private ArrayList<Column> columns;
    private int rowCount;
    private int columnCount;
    public Table(String name) {
        this.name = name;
        columnCount = 0;
        columns = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addColumn(Column column) {
        columns.add(column);
        columnCount++;
        rowCount = (rowCount < column.getValues().size()) ? column.getValues().size() : rowCount;
    }

    public ArrayList<Column> getColumns() {
        return columns;
    }
    public int getRowCount() {
        return rowCount;
    }
    public int getColumnCount() {
        return columnCount;
    }

    public ArrayList<String>[] formateRow()
    {
        ArrayList<String>[] rows = new ArrayList[rowCount];
       
        ArrayList<String> row ;
        for (int i = 1; i < rowCount + 1; i++) {
            row = new ArrayList<>();
            for (Column column : columns) {
                row.add(column.getValues().get(String.valueOf(i)));
            }
            rows[i - 1] = row;
        }

        return rows;
    }
}
