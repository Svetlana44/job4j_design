/*
есть файл CSV со столбцами name, age, birthDate, education, children
 и программа запускается таким образом:
-path=./data/file.csv -delimiter=; -out=stdout -filter=name,age
 */
package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import static java.io.File.createTempFile;

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

        try (Scanner scanner = new Scanner(new FileReader(path));
             FileWriter fileWriter = new FileWriter(out, true)
        ) {

            while (scanner.hasNext()) {
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
                StringJoiner stringJoiner = new StringJoiner(delimiter);
                indexiesFilter.forEach(i -> stringJoiner.add(str[i]));

                fileWriter.write(stringJoiner + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader();
        File directory;
        try {
            directory = Files.createTempDirectory("temp").toFile();
            File target = createTempFile("pathOut", "txt", directory);

            ArgsName argsName = ArgsName.of(new String[]{
                    args[0],
                    args[1],
                    "-out=" + target.getAbsolutePath(),
                    args[3]});
            csvReader.handle(argsName);
            String output = args[2].substring(5);
            if (Objects.equals(output, "stdout")) {
                System.out.println(Files.readString(target.toPath()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
