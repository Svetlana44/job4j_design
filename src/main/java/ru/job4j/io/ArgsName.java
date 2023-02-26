package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public Map<String, String> getValues() {
        return values;
    }

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("That key does not exist.");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            String[] keyValue = arg.split("=", 2);
            validation(keyValue);

            values.put(keyValue[0].substring(1), keyValue[1]);
        }
        /* parse args to values. */
    }

    private void validation(String[] keyValue) {

        if (!(keyValue.length == 2)) {
            throw new IllegalArgumentException("No equal.");
        }
        if (keyValue[0].substring(1).isEmpty()) {
            throw new IllegalArgumentException("Empty key");
        }
        if (keyValue[1].isEmpty()) {
            throw new IllegalArgumentException("Empty value");
        }
        if (!keyValue[0].startsWith("-")) {
            throw new IllegalArgumentException("Key mast start with -");
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No arguments");
        }

        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}