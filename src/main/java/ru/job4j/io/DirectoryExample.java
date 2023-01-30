/*
Директория отличается от файла тем, что директория может хранить другие файлы и директории,
 поэтому у нее есть некоторые методы, позволяющие получать информацию о содержащихся в директории элементах
 */
package ru.job4j.io;

import java.io.File;
import java.io.IOException;

public class DirectoryExample {
    public static void main(String[] args) throws IOException {
        File dir = new File("src/main/java/ru/job4j/io/files/directory");
        dir.mkdirs();
        File target = new File("src/main/java/ru/job4j/io/files");
        File file1 = new File("src/main/java/ru/job4j/io/files/file1.txt");
        file1.createNewFile();
        File file2 = new File("src/main/java/ru/job4j/io/files/directory/file2.txt");
        file2.createNewFile();
        /*  метод list() возвращает массив строк с именами файлов и директорий, содержащихся в директории files */
        String[] list = target.list();
        for (String f : list) {
            System.out.println(f);
        }
        /* метод listFiles() возвращает массив объектов типа File
         с инкапсулированными путями расположения этих объектов в файловой системе,
          содержащихся в директории files */
        File[] listFiles = target.listFiles();
        for (File f : listFiles) {
            System.out.println(f);
            /*
Ввовд:
directory
file1.txt
src\main\java\ru\job4j\io\files\directory
src\main\java\ru\job4j\io\files\file1.txt
             */
        }

        /* Удалить файл или директорию можно с помощью метода delete().
             Каталог можно удалить, только если он пустой! */
        file1.delete();
        dir.delete();
        /* deleteOnExit(), который не удаляет файл сразу, а удалит его по завершении работы программы */
        file1.deleteOnExit();
    }
}