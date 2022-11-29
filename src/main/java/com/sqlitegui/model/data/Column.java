package com.sqlitegui.model.data;

import java.util.ArrayList;
import java.util.HashMap;

public class Column {
    private String name;
    private ArrayList<String> keys;
    private HashMap<String, String> values;

    public Column(String name) {
        this.name = name;
        this.keys = new ArrayList<>();
        this.values = new HashMap<String, String>();
    }

    public void addValueToColumn(String id, String value) {
        values.put(id, value);
        keys.add(id);
    }
    
    public String getName() {
        return name;
    }
    
    public HashMap<String, String> getValues() {
        return values;
    }
    public ArrayList<String> getKeys() {
        return keys;
    }
}
