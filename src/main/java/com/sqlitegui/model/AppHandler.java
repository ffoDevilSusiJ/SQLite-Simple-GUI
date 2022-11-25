package com.sqlitegui.model;

import java.io.File;

import com.sqlitegui.model.data.DataBaseHandler;

public class AppHandler {
    private static File file;

    public static void load()
    {
        DataBaseHandler dHandler = new DataBaseHandler(file.getAbsolutePath(), "root", "");
        new Thread(() -> dHandler.getTablesFromDB()).start();;
        
    }


    public static void setFile(File file) {
        AppHandler.file = file;
        
    }
    public static File getFile() {
        return file;
    }
}
