package com.sqlitegui.model.data;

import java.util.HashMap;

public class Column {
    private String name;
    private HashMap<String, String> values;

    public Column(String name) {
        this.name = name;
        this.values = new HashMap<String, String>();
    }

    public void addValueToColumn(String id, String value) {
        values.put(id, value);
    }

    public HashMap<String, String> getValues() {
        return values;
    }
}
