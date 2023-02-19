package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.FileVisitResult.CONTINUE;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> paths = new HashMap<>();

    public Map<FileProperty, List<Path>> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());

        paths.putIfAbsent(fileProperty, new ArrayList<Path>());
        paths.get(fileProperty).add(file);

        return CONTINUE;
    }

    @Override
    public String toString() {
        return "DuplicatesVisitor{" + "paths=" + paths + '}';
    }
}