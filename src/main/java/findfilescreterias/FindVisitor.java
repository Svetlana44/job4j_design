package findfilescreterias;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import static java.nio.file.FileVisitResult.CONTINUE;

public class FindVisitor implements FileVisitor<Path> {

    public List<Path> files = new ArrayList<>();
    public String paramSearch;
    public String n;

    public FindVisitor(String paramSearch, String n) {
        this.paramSearch = paramSearch;
        this.n = n;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        validationFile(file);
        return CONTINUE;
    }

    public boolean validationFile(Path path) {
        boolean result = false;
        String file = path.getFileName().toString();

        if (paramSearch.equals("name")) {
            if (file.equals(n)) {
                files.add(path);
                result = true;
            }
        }
        /* Любое имя файла, но с известным расширением. Например, "*.txt" */
        if (paramSearch.equals("mask")) {
            String nameOfFile = String.valueOf(path.getFileName());
            int index = nameOfFile.indexOf('.');
            String mask = nameOfFile.substring(index);
            if (Objects.equals("*" + mask, n)) {
                files.add(path);
                result = true;
            }
        }
        if (paramSearch.equals("regex")) {
            Pattern pattern = Pattern.compile(n);
            if (pattern.matcher(file).matches()) {
                files.add(path);
                result = true;

            }
        }
        return result;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }

    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        Files.walkFileTree(start, new FindVisitor("name", "FindVisitor.java"));

    }
}
