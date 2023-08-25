/*
 * Доработайте код программы чтобы чтение драйвера, url, имени пользователя и пароля происходило из файла app.properties.
 *  Для чтения используйте класс Config,
 *  который Вы писали, когда проходили блок по IO.*/

package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

/*        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "password";  */

        Config config = new Config("data/app.properties");

        Class.forName(config.value("org.postgresql.Driver"));
        String url = config.value("jdbc:postgresql://localhost:5432/idea_db");
        String login = config.value("postgres");
        String password = config.value("password");

        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}