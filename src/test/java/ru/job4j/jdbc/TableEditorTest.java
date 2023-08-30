package ru.job4j.jdbc;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class TableEditorTest {

    @Test
    void whenCheckProperty() {
        Properties properties = new Properties();
//        ClassLoader loader = Properties.class.getClassLoader();
        try (FileInputStream in = new FileInputStream("src/main/resources/app.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        TableEditor tableEditor = new TableEditor(properties);
        Assertions.assertThat(properties.getProperty("url")).isEqualTo("jdbc:postgresql://localhost:5432/some_db");
        Assertions.assertThat(properties.getProperty("username")).isEqualTo("postgres");
        Assertions.assertThat(properties.getProperty("password")).isEqualTo("password");
    }
}