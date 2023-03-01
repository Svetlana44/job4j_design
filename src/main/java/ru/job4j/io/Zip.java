/*
-d - directory - которую мы хотим архивировать.
-e - exclude - исключить файлы с расширением class.
-o - output - во что мы архивируем.
 */
package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {

        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile().getPath()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validation(Map<String, String> map) {
        if (map.size() != 3) {
            throw new IllegalArgumentException("Need three arguments.");
        }
        if (!map.containsKey("d")) {
            throw new IllegalArgumentException("Need directory.");
        }
        if (!map.containsKey("e")) {
            throw new IllegalArgumentException("Need exclude files.");
        }
        if (!map.containsKey("o")) {
            throw new IllegalArgumentException("Need output object.");
        }

        if (!(Files.exists(Paths.get(map.get("d"))))) {
            System.out.println(Paths.get(map.get("d")).toFile());
            throw new IllegalArgumentException("Directory does not exist.");
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(new String[]{"-d=C:\\projects\\job4j_design\\src\\main/java/ru/job4j/io/", "-e=.class", "-o=project.zip"});
        validation(argsName.getValues());

        List<Path> paths = Search.search(Paths.get(argsName.getValues().get("d")), e -> e != Paths.get(argsName.getValues().get("e")));
        Zip zip = new Zip();
        zip.packFiles(paths, new File(argsName.getValues().get("o")));

        Zip zip2 = new Zip();
        zip2.packFiles(new ArrayList<>(),
                new File("./1.zip")
        );
    }
}