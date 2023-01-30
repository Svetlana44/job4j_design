/*
У созданного файла или директории мы можем получить свойства с помощью нижеперечисленных методов.
 Файлы и директории создаются только тогда, когда они еще не существуют,
  поэтому перед каждым запуском метода main удаляйте созданный файл из директории io/files
  или директорию files целиком.
 */
package ru.job4j.io;

import java.io.File;
import java.io.IOException;

public class FileExample {
    public static void main(String[] args) throws IOException {
        File directory = new File("src/main/java/ru/job4j/io/files");
        /* Метод mkdir() создает директорию только при наличии всех остальных директорий в переданном пути.
        Если по пути к целевой директории нужно создавать поддиректории, то нужно использовать
        метод mkDirs() - он создает целевую директорию и все поддиректории, если они еще не существуют. */
        directory.mkdir();
        File file = new File("src/main/java/ru/job4j/io/files/file.txt");
        /* В следующей строке из объекта типа File создается файл на диске по пути, который хранит объект file */
        file.createNewFile();
        System.out.println("Файл/директория существует?: " + file.exists());

        System.out.println("Это директория?: " + directory.isDirectory());
        System.out.println("Это директория?: " + file.isDirectory());

        System.out.println("Это файл?: " + file.isFile());
        System.out.println("Имя файла: " + file.getName());
        System.out.println("Путь к файлу: " + file.getPath());
        System.out.println("Путь к файлу абсолютный?: " + file.isAbsolute());
        System.out.println("Родительская директория файла: " + file.getParent());
        System.out.println("Абсолютный путь к файлу: " + file.getAbsoluteFile());
        System.out.println("Абсолютный путь к директории: " + directory.getAbsolutePath());
        System.out.println("Доступен для чтения?: " + file.canRead());
        System.out.println("Доступен для записи?: " + file.canWrite());
        /* Метод length() выводит длину файла в байтах: */
        System.out.println("Длина файла: " + file.length());
        File newFile = new File("src/main/java/ru/job4j/io/files/newFile.txt");
        /* Метод renameTo() переименовывает файл или директорию. Возвращает true, если успешно, иначе false.
         Данный метод может работать не во всех файловых системах, поэтому нужно проверять результат.*/
        System.out.println("Переименование файла в newFile. Успешно?: " + file.renameTo(newFile));
    }
}