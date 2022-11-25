package com.sqlitegui.model.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public void getTablesFromDB() {
        Connection conn = getSqlConnection();

        String quary = "SELECT * FROM ";
        String[] tables = getAllTablesList();
        Table table;
        try {
            for (String string : tables) {
                PreparedStatement ps = conn.prepareStatement(quary + string);
                // ps.setString(1, string);
                ResultSet rs = ps.executeQuery();
                table = new Table(string);
                for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                    if (i != 1 ) rs = ps.executeQuery();
                    Column column = new Column(rs.getMetaData().getColumnName(i));
                    int j = 0; 
                    while (rs.next()) {
                        j++;
                        if(i == 1) column.addValueToColumn(String.valueOf(j), rs.getString(i));
                         else column.addValueToColumn(rs.getString(1), rs.getString(i));
                    }
                    table.addColumn(column);
                }
                
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String[] getAllTablesList() {
        String[] tables = null;
        Connection conn = getSqlConnection();
        String quary = "SELECT name FROM sqlite_master WHERE type='table'";
        ResultSet tableSet;
        try (PreparedStatement statement = conn.prepareStatement(quary)) {
            tableSet = statement.executeQuery();
            tables = new String[tableSet.getMetaData().getColumnCount()];
            while (tableSet.next()) {
                tables[tableSet.getRow() - 1] = tableSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;

    }
}
