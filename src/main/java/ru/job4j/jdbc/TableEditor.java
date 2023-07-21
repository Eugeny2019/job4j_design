package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws Exception {
        var query = String.format("create table if not exists %s ()", tableName);
        executeQuery(query);
    }

    public void dropTable(String tableName) throws Exception {
        var query = String.format("drop table %s", tableName);
        executeQuery(query);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        var query = String.format("alter table %s add column %s %s", tableName, columnName, type);
        executeQuery(query);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        var query = String.format("alter table %s drop column %s", tableName, columnName);
        executeQuery(query);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        var query = String.format("alter table %s rename column %s to %s", tableName, columnName, newColumnName);
        executeQuery(query);
    }

    private void executeQuery(String query) throws Exception {
        try (var statement = connection.createStatement()) {
            statement.execute(query);
        }
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
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

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        String tableName = "demo_table";
        String columnName1 = "column1";
        String columnName2 = "column2";
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        }
        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.createTable(tableName);
            System.out.println("Table created:");
            System.out.println(tableEditor.getTableScheme(tableName));

            tableEditor.dropTable(tableName);
            System.out.println("\nTable dropped.");

            tableEditor.createTable(tableName);
            System.out.println("\nTable created. Two columns added into table.");
            System.out.println(tableEditor.getTableScheme(tableName));
            tableEditor.addColumn(tableName, columnName1, "text");
            System.out.println(tableEditor.getTableScheme(tableName));
            tableEditor.addColumn(tableName, columnName2, "text");
            System.out.println(tableEditor.getTableScheme(tableName));

            tableEditor.dropColumn(tableName, columnName2);
            System.out.println("\nOne column dropped.");
            System.out.println(tableEditor.getTableScheme(tableName));

            tableEditor.renameColumn(tableName, columnName1, columnName2);
            System.out.println("\nColumn renamed.");
            System.out.println(tableEditor.getTableScheme(tableName));
        }
    }
}