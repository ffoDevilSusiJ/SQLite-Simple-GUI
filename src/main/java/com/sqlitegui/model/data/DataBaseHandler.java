package com.sqlitegui.model.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataBaseHandler {

    private String URL;
    private String user;
    private String password;

    public DataBaseHandler(String URL, String user, String password) {
        this.URL = URL;
        this.user = user;
        this.password = password;
    }

    private Connection getSqlConnection() {

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + URL, user, password);
            return conn;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Table> getTablesFromDB() {
        Connection conn = getSqlConnection();

        String quary = "SELECT * FROM ";
        ArrayList<String> tablesStrings = getAllTablesList();
        ArrayList<Table> tables = new ArrayList<>();
        for (String string : tablesStrings) {
            try (PreparedStatement ps = conn.prepareStatement(quary + string)) {

                ResultSet rs = ps.executeQuery();
                Table lolacTable = new Table(string);
                for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                    if (i != 1)
                        rs = ps.executeQuery();
                    Column column = new Column(rs.getMetaData().getColumnName(i));
                    int j = 0; // Row index
                    while (rs.next()) {
                        j++;
                        column.addValueToColumn(String.valueOf(j), rs.getString(i));
                    }
                    lolacTable.addColumn(column);
                }
                tables.add(lolacTable);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tables;
    }

    private ArrayList<String> getAllTablesList() {
        ArrayList<String> tables = new ArrayList<>();
        Connection conn = getSqlConnection();
        String quary = "SELECT name FROM sqlite_master WHERE type='table'";
        ResultSet tableSet;
        try (PreparedStatement statement = conn.prepareStatement(quary)) {
            tableSet = statement.executeQuery();
            while (tableSet.next()) {
                tables.add(tableSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;

    }

    public void saveAllChanges(ArrayList<Table> tables) {
        Connection conn = getSqlConnection();
        String quary = "Update";
        ResultSet tableSet;
           
            for (Table table : tables) {
                for (int i = 0; i < table.getColumnCount(); i++) {
                    Column column = table.getColumns().get(i);
                    quary = "UPDATE " + table.getName() + " SET ";
                    for (int j = 0; j < table.getRowCount(); j++) {
                        quary += column.getName() + " = '" + column.getValues().get(String.valueOf(j + 1)) + "'";
                        quary += " WHERE " + table.getColumns().get(0).getName() + " = " + column.getKeys().get(j);
                        System.out.println(quary);
                        quary = "UPDATE " + table.getName() + " SET ";
                    }
                    
                }
                
            }
            


       

    }
}
