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

    private boolean peekFilter(String string) {
        if ((!string.matches(".+=.+")) && (!string.startsWith("#")) && (string.trim().length() != 0)) {
            throw new IllegalArgumentException("string \"" + string + "\" this does breaking pattern");
        }
        return ((string.contains("=")) && !(string.startsWith("#")));
    }

    public void load() {
        try (BufferedReader fileBuf = new BufferedReader(new FileReader(path))) {
 /*
 values = fileBuf.lines().filter(string -> peekFilter(string)) можно заменить на method reference    this::peekFilter
  */
            values = fileBuf.lines().filter(this::peekFilter)
                    .map(string -> string.split("=", 2))
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