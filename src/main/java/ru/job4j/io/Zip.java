/*
-d - directory - которую мы хотим архивировать.
-e - exclude - исключить файлы с расширением class.
-o - output - во что мы архивируем.
args[]=new String[]{"-d=C:\\projects\\job4j_design\\src\\main/java/ru/job4j/io/", "-e=.class", "-o=project.zip"}
 */
package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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

    private static void validation(ArgsName argsName) {
         /*
        проверяете, что параметр по ключу d представляет собой путь, он существует в файловой системе и ведет к каталогу.
         */
        if (argsName.get("d").isEmpty()) {
            throw new IllegalArgumentException("Need directory.");
        }
        if (!(Paths.get(argsName.get("d")).toFile().exists()) || !(Paths.get(argsName.get("d")).toFile().isDirectory())) {
            throw new IllegalArgumentException("Directory not exists.");
        }
        if (argsName.get("e").isEmpty()) {
            throw new IllegalArgumentException("Need exclude files.");
        }
        if ((!argsName.get("e").startsWith(".")) || (!(argsName.get("e").length() > 1))) {
            throw new IllegalArgumentException("parametr -e not valid.");
        }

        if (argsName.get("o").isEmpty()) {
            throw new IllegalArgumentException("Need output object.");
        }
        if (!argsName.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("arhiv don`t end with \".zip\"");
        }

        if (!(Files.exists(Paths.get(argsName.get("d"))))) {
            System.out.println(Paths.get(argsName.get("d")).toFile());
            throw new IllegalArgumentException("Directory does not exist.");
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Need three arguments.");
        }

        ArgsName argsName = ArgsName.of(args);
        validation(argsName);

        List<Path> paths = Search.search(Paths.get(argsName.get("d")), e -> e != Paths.get(argsName.get("e")));
        Zip zip = new Zip();
        zip.packFiles(paths, new File(argsName.get("o")));

        Zip zip2 = new Zip();
        zip2.packFiles(new ArrayList<>(),
                new File("./1.zip")
        );
    }
}