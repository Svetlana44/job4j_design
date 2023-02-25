package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("That key does not exist.");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No elements.");
        }
        for (String str : args) {
            String[] pair = str.split("=", 2);
            validation(pair);
        }

        /* parse args to values. */

    }

     void validation(String[] pair) {
        for (String str : pair) {

            if (!(pair[0].length() > 1)) {
                throw new IllegalArgumentException("Empty key");
            }
            if (!(pair[1].length() > 0)) {
                throw new IllegalArgumentException("Empty value");
            }
            if (!pair[0].startsWith("-")) {
                throw new IllegalArgumentException("Key mast start with -");
            }
            values.put(pair[0].substring(1), pair[1]);
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