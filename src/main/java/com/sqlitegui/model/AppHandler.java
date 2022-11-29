package com.sqlitegui.model;

import java.io.File;
import java.util.ArrayList;

import com.sqlitegui.model.data.DataBaseHandler;
import com.sqlitegui.model.data.Table;

public class AppHandler {
    private static File file;
    private static ArrayList<Table> tables;
    private static DataBaseHandler dHandler;
    public static void load()
    {
        dHandler = new DataBaseHandler(file.getAbsolutePath(), "root", "");
        new Thread(() -> {
            tables = dHandler.getTablesFromDB();

        }).start();
        
    }


    public static void setFile(File file) {
        AppHandler.file = file;
        
    }
    public static DataBaseHandler getdHandler() {
        return dHandler;
    }
    public static File getFile() {
        return file;
    }

    public static ArrayList<Table> getTables() {
        return tables;
    }
}
