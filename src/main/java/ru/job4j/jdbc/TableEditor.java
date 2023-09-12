package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    /*
    Важно! Ресурсные файлы (*.properties) нужно хранить в папке resources по пути src/main/resources.
    А для загрузки настроек использовать ClassLoader, как в примере ниже,
     который будет искать файлы по названию именно в папке resources.

можно указать значение проперти по умолчанию(второй проперти лист), если нет такого по ключу
Properties defaultProperties = new Properties();
defaultProperties.setProperty("preferredLanguage", "Danish");
Properties newProperties = new Properties(defaultProperties);
String language = newProperties.getProperty("preferredLanguage");*/

    private void initConnection() {
        properties = new Properties();
        /* для лоадера нужно указывать название СВОЕГО класса  */
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
/* можно ещё вот так загрузит проперти:
Properties properties = new Properties();
try(FileReader fileReader = new FileReader("data/props.properties")){
    properties.load(fileReader);
} catch (IOException e) {
    e.printStackTrace();
} */
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("DriverManager");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        String creatTableSql = "CREATE TABLE IF NOT EXISTS " + tableName + "(); COMMIT;";
        statementExecute(creatTableSql);
    }

    public void dropTable(String tableName) {
        String dropTableSql = "DROP TABLE IF EXISTS " + tableName;
        statementExecute(dropTableSql);
    }

    public void addColumn(String tableName, String columnName, String type)  {
        String addColumnSql = "ALTER TABLE " + tableName + " ADD " + columnName + " " + type + "; COMMIT;";
        statementExecute(addColumnSql);
    }

    public void dropColumn(String tableName, String columnName) {
        String dropColumnSql = "ALTER TABLE " + tableName + " DROP COLUMN " + columnName;
        statementExecute(dropColumnSql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String renameColumnSql = "ALTER TABLE " + tableName + " RENAME COLUMN " + columnName + " TO " + newColumnName + "; COMMIT;";
        statementExecute(renameColumnSql);
    }

    public boolean ifExists(String tableName) {
        boolean result = false;
        try (Statement statement = connection.createStatement()) {
            String ifExistsTable = "SELECT EXISTS (SELECT 1 FROM " + tableName + ");";
            result = statement.execute(ifExistsTable);
        } catch (SQLException e) {
            System.out.println("Table is not exists.");
        }
        return result;
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1 ;", tableName
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

    public void checkProperty() {
        initConnection();
    }

    public void statementExecute(String statementStr) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(statementStr);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        TableEditor tableEditor = new TableEditor(null);

        tableEditor.initConnection();

        tableEditor.createTable("test");
        System.out.println(tableEditor.getTableScheme("test"));

        tableEditor.dropTable("test");
        if (tableEditor.ifExists("test")) {
            System.out.println(tableEditor.getTableScheme("test"));
        }

        if (!tableEditor.ifExists("test")) {
            tableEditor.createTable("test");
        }
        tableEditor.addColumn("test", "testColumn", "text");
        System.out.println(tableEditor.getTableScheme("test"));

        tableEditor.renameColumn("test", "testColumn", "testColumn2");
        System.out.println(tableEditor.getTableScheme("test"));
    }
}