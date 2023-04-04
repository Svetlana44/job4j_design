/*
есть файл CSV со столбцами name, age, birthDate, education, children
 и программа запускается таким образом:
-path=./data/file.csv -delimiter=; -out=stdout -filter=name,age
 */
package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        String[] filterPoints = filter.split(",");
        Map<String, Integer> headers = new HashMap<>();
        boolean flagHeaders = false;
        List<Integer> indexiesFilter = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();

        try (Scanner scanner = new Scanner(new FileReader(path))
        ) {

            while (scanner.hasNext()) {
                StringJoiner stringJoiner = new StringJoiner(delimiter);

                String[] str = scanner.nextLine().split(delimiter);
                /* читает заголовки столбцов, записывает их в массив и составляет LinckedList с индексами используемых столбцов  */
                if (!flagHeaders) {
                    for (int i = 0; i < str.length; i++) {
                        headers.put(str[i], i);
                    }
                    flagHeaders = true;
                    /* нужно из хэдэров выбрать значения фильтра и расставить их в порядке нахождения по фильтру*/
                    for (String s : filterPoints) {
                        indexiesFilter.add(headers.get(s));
                    }
                }
                int index = 0;
                for (int i = 0; i < indexiesFilter.size() - 1; i++) {
                    stringBuilder.append(str[i]).append(delimiter);
                    index++;
                }
                stringBuilder.append(str[index]);
                stringBuilder.append(System.lineSeparator());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        presentation(stringBuilder, out);
    }

    private static void validation(ArgsName argsName, String[] params) {

        if (!(Files.exists(Paths.get(argsName.get("path"))))) {
            throw new IllegalArgumentException("File does not exist.");
        }
        if (!((params[2]).contains(".csv") || (("-out=stdout").equals(params[2])))) {
            throw new IllegalArgumentException("Out isn`t correct.");
        }
        if (!(("-delimiter=;").equals(params[1]))) {
            throw new IllegalArgumentException("Demiliter isn`t valid.");
        }
    }

    static void presentation(StringBuilder stringBuilder, String out) {
        if (!out.equals("stdout")) {
            try {
                try (FileWriter fileWriter = new FileWriter(out)) {
                    fileWriter.write(String.valueOf(stringBuilder));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(stringBuilder);
        }
    }

    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader();
        ArgsName argsName = ArgsName.of(new String[]{
                args[0],
                args[1],
                args[2],
                args[3]
        });
        validation(argsName, args);
        try {
            csvReader.handle(argsName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
