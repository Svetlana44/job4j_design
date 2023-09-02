package ru.job4j.jdbc.spamer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    private void loadProperties(String fileName) {
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream(fileName)) {
            cfg.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(s -> {
                        if (s.contains(";")) {
                            String[] strs = s.split(";", 2);
                            if (strs.length == 2 && !(strs[0].equals("")) && strs[1].endsWith(";") && !(strs[1].equals(" ;"))) {
                                users.add(new User(strs[0], strs[1].substring(0, strs[1].length() - 2)));
                            }
                        } else {
                            throw new IllegalArgumentException("Not valid users in the file.");
                        }
                    }
            );
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = connection.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    private void createTable(String tableName) throws ClassNotFoundException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS " + tableName
                    + " (id serial primary key,"
                    + "name text,"
                    + "email text);"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        ImportDB db = new ImportDB(cfg, "data/spammer/dump.txt");
        db.loadProperties("spammer.properties");
        db.createTable("users");
        db.save(db.load());
    }
}
