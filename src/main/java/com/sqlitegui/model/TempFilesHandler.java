package com.sqlitegui.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.ArrayList;
import java.util.List;

import com.sqlitegui.model.file.RecentFile;

public class TempFilesHandler {
    public static void addToRecentFile(String data)
    {
        RecentFile file;
        try {
            file = new RecentFile("recent.dat");
            file.createNewFile();

            if(file.recordExists(data)) return;

            FileWriter writer = new FileWriter(file, true);
            writer.write(data + "\n");
            writer.flush();
            writer.close(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public static List<String> getRecentFiles()
    {
        File file = new RecentFile("recent.dat");
        try {
            file.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<String> list = new ArrayList<>();
            String line;
            while ((line = reader.readLine())!= null) {
                list.add(line);
                
            }
            reader.close();
            return list;
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        return null;
    }
}
