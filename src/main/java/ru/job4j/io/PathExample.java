package ru.job4j.io;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {
    public static void main(String[] args) throws IOException {
        Path dir = Paths.get("path/paths");
        Files.createDirectories(dir);
        Path path = Path.of("path/paths/path.txt");
        /*        Files.createFile(path);  */
        System.out.println("Файл/директория существует?: " + Files.exists(path));
        System.out.println("Это директория?: " + Files.isDirectory(path));
        System.out.println("Это файл?: " + Files.isRegularFile(path));
        System.out.println("Имя файла: " + path.getFileName());
        System.out.println("Путь к файлу абсолютный?: " + path.isAbsolute());
        System.out.println("Родительская директория файла: " + path.getParent());
        System.out.println("Абсолютный путь к файлу: " + path.toAbsolutePath());
        System.out.println("Абсолютный путь к директории: " + dir.toAbsolutePath());
        System.out.println("Доступен для чтения?: " + Files.isReadable(path));
        System.out.println("Доступен для записи?: " + Files.isWritable(path));

        /*  файл перемещается из директории paths в директорию path  */
        Files.move(path, Path.of("path/path.txt"));

        /*   Метод Files.delete() в отличие от File.delete() выдаст полезную информацию в виде исключения,
         если указанный объект не будет удален  (нельзя удалить непустую директорию)
        Files.delete(dir); */

        Path target = Paths.get("path");
        Path path1 = Path.of("path/paths/path1.txt");
        Files.createFile(path1);
        Path path3 = Path.of("path/path2.txt");
        Files.createFile(path3);
        /*  newDirectoryStream() возвращает поток, содержащий файлы и директории,
        находящиеся в директории path (без вложенных).  */
        DirectoryStream<Path> paths = Files.newDirectoryStream(target);
        paths.forEach(System.out::println);
    }
}