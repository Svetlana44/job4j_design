package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {

        validation(args);

        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) {
        SearchFiles searcher = new SearchFiles(condition);
        try {
            Files.walkFileTree(root, searcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searcher.getPaths();
    }

    private static void validation(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Need two arguments.");
        }
        if (!Files.exists(java.nio.file.Paths.get(args[0]))) {
            throw new IllegalArgumentException("Directory does not exist.");
        }
        if (!(args[1].startsWith(".") && args[1].length() > 1)) {
            throw new IllegalArgumentException("File not valid.");
        }

    }
}