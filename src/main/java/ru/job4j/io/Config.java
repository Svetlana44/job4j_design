package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    public Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader fileBuf = new BufferedReader(new FileReader(path))) {
            values = fileBuf.lines().filter(line -> (line.contains("=")) && !(line.contains("#")))
                    .peek(string -> {
                        if (!string.matches(".+=.+")) {
                            throw new IllegalArgumentException("this does breaking pattern");
                        }
                    })
                    .map(string -> string.split("=", 2))
                    .peek(arrayStr -> {
                        if (arrayStr.length < 2) {
                            throw new IllegalArgumentException();
                        }
                    })
                    .collect(Collectors
                            .toMap(str -> str[0], str -> str[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner fileToString = new StringJoiner(System.lineSeparator());
        try (BufferedReader fileBuf = new BufferedReader(new FileReader(path))) {
            fileBuf.lines().forEach(fileToString::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileToString.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("data/app.properties");
        System.out.println(config);
/*      config.load();
        System.out.println(config.values.keySet());
        System.out.println(config.values.values()); */
    }
}