/*
Важно, аннотацию @TempDir нужно использовать, когда программа записывает результат в файл.
Аннотацией @TempDir указывается временная папка, которая будет использоваться для тестов.
После окончания теста папка будет удалена.
 */
package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AbuseTest {

    @Test
        /* Аннотация @TempDir указывает, что папка Path tempDir будет временной */
    void drop(@TempDir Path tempDir) throws IOException {
        /* создаем файл, куда будет выведен результат */
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("hello foolish dude");
            out.println("java job4j php");
        }
        File target = tempDir.resolve("target.txt").toFile();
        /* выполняем действие программы и читаем полученный результат из файла target */
        Abuse.drop(source.getAbsolutePath(), target.getAbsolutePath(), List.of("foolish", "php"));

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("hello dude java job4j ").isEqualTo(rsl.toString());
    }
}