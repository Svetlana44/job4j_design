package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("C:/projects/job4j_design/data"), duplicatesVisitor);
        duplicatesVisitor.getPaths().forEach(DuplicatesFinder::accept);
    }

    private static void accept(FileProperty fileProperty, ArrayList<Path> pathsList) {
        if (pathsList.size() > 1) {
            pathsList.forEach(p -> System.out.println("" + p + ";  size: " + p.toFile().length()));
        }
    }
}