package ru.job4j.jdbc;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class TableEditorTest {

    Properties properties = new Properties();

    @BeforeEach
    void connection() {

        /* для лоадера нужно указывать название СВОЕГО класса  */
        ClassLoader loader = TableEditor.class.getClassLoader();
        try (FileInputStream in = new FileInputStream("src/main/resources/app.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void whenCheckProperty() {

        connection();
        TableEditor tableEditor = new TableEditor(properties);
        Assertions.assertThat(properties.getProperty("url")).isEqualTo("jdbc:postgresql://localhost:5432/some_db");
        Assertions.assertThat(properties.getProperty("username")).isEqualTo("postgres");
        Assertions.assertThat(properties.getProperty("password")).isEqualTo("password");
    }

    @Test
    void whenGetTableScheme() throws Exception {

        TableEditor tableEditor = new TableEditor(properties);
        tableEditor.createTable("test2");
        String expected = "------------------------------"
                + System.lineSeparator()
                + "NAME           |TYPE           "
                + System.lineSeparator()
                + "------------------------------"
                + System.lineSeparator();

        String actual = tableEditor.getTableScheme("test2");
        Assertions.assertThat(expected).isEqualTo(actual);
    }


}