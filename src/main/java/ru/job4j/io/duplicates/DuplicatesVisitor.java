package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.FileVisitResult.CONTINUE;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, ArrayList<Path>> paths = new HashMap<>();

    public Map<FileProperty, ArrayList<Path>> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (paths.containsKey(new FileProperty(file.toFile().length(), file.getFileName().toString()))) {
            paths.get(new FileProperty(file.toFile().length(), file.getFileName().toString())).add(file);
        } else {
            ArrayList<Path> list = new ArrayList<>();
            list.add(file);
            paths.put(new FileProperty(file.toFile().length(), file.getFileName().toString()), list);
        }
        return CONTINUE;
    }

    @Override
    public String toString() {
        return "DuplicatesVisitor{" + "paths=" + paths + '}';
    }
}