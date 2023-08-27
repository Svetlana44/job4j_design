package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class Statement {

    private static Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "password";
        return DriverManager.getConnection(url, login, password);
    }

}
