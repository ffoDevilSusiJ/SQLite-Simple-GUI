package com.sqlitegui.model.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RecentFile extends File {

    public RecentFile(String path) {
        super(path);
    }

    public boolean recordExists(String data) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this));
            
            reader.mark(1000);
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.equals(data)) {
                    reader.reset();
                    ArrayList<String> records = new ArrayList<>();
                    while ((line = reader.readLine()) != null) {
                        records.add(line);
                    }
                    for (int i = 0; i < records.size(); i++) {
                        if (records.get(i).equals(data)) {
                            String temp = records.get(0);
                            records.set(0, records.get(i));
                            records.set(i, temp);
                        }
                    }
                    FileWriter writer = new FileWriter(this);
                    for (String record : records) {
                        writer.append(record + "\n");
                        writer.flush();
                    }
                    writer.close();
                    return true;
                }

            }

        } catch (

        IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
