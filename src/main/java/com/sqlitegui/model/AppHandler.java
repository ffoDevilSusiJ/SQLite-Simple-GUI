package com.sqlitegui.model;

import java.io.File;

public class AppHandler {
    private static File file;




    public static void setFile(File file) {
        AppHandler.file = file;
        
    }
    public static File getFile() {
        return file;
    }
}
