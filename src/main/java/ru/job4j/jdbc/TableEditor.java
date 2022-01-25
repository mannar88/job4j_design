package ru.job4j.jdbc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Statement statement;
    private final Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileReader(new File("./src/main/resources/app.properties")));
        String tableName = properties.getProperty("tableName");
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("login"), properties.getProperty("password"));
        TableEditor tableEditor = new TableEditor(properties);
        tableEditor.createTable(tableName);
        System.out.println(getTableScheme(connection, tableName));
        tableEditor.addColumn(tableName, properties.getProperty("columnName"), properties.getProperty("type"));
        System.out.println(getTableScheme(connection, tableName));
        tableEditor.renameColumn(tableName, properties.getProperty("columnName"), properties.getProperty("columnNewName"));
        System.out.println(getTableScheme(connection, tableName));
        tableEditor.dropColumn(tableName, properties.getProperty("columnNewName"));
        System.out.println(getTableScheme(connection, tableName));
        tableEditor.dropTable(tableName);
        System.out.println(getTableScheme(connection, tableName));
    }

    private void initConnection() {
        try {
            Class.forName(properties.getProperty("driver"));
            connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("login"), properties.getProperty("password"));
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        try {
            statement.execute("create table " + tableName + "()");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable(String tableName) {
        try {
            statement.execute("drop table " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try {
            statement.execute("ALTER TABLE " + tableName + " ADD COLUMN " + columnName + " " + type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        statement.execute("ALTER TABLE " + tableName + " DROP COLUMN " + columnName);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try {
            statement.execute("ALTER TABLE " + tableName + " RENAME COLUMN " + columnName + " TO " + newColumnName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}