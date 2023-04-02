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
import java.nio.file.Files;
import java.nio.file.Paths;
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

    private static void validation(ArgsName argsName, String[] params) {
        if (argsName.get("path").isEmpty()) {
            throw new IllegalArgumentException("Need path.");
        }
        if (argsName.get("delimiter").isEmpty()) {
            throw new IllegalArgumentException("Need delimiter.");
        }
        if (argsName.get("out").isEmpty()) {
            throw new IllegalArgumentException("Need out.");
        }
        if (argsName.get("filter").isEmpty()) {
            throw new IllegalArgumentException("Need filter.");
        }

        if (!(Files.exists(Paths.get(argsName.get("path"))))) {
            System.out.println(Paths.get(argsName.get("path")).toFile());
            throw new IllegalArgumentException("File does not exist.");
        }
        if (!((("-out=path").equals(params[2])) || (("-out=stdout").equals(params[2])))) {
            throw new IllegalArgumentException("Out isn`t correct.");
        }
    }

    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader();
        try (Scanner input = new Scanner(System.in)) {
            /* не путать параметр out это вывод в файл или консоль, и out для ArgsName - это файл вывода */
            String[] pointers = new String[]{
                    "-path=",
                    "-delimiter=",
                    "-out=",
                    "-filter="};
            int point = 0;
            String[] params = new String[4];
            for (int i = 0; i < 4; i++) {
                System.out.println(pointers[i]);
                params[i] = pointers[i] + input.nextLine();
            }
            File directory;
            directory = Files.createTempDirectory("temp").toFile();
            File target = createTempFile("pathOut", "txt", directory);

            ArgsName argsName = ArgsName.of(new String[]{
                    params[0],
                    params[1],
                    "-out=" + target.getAbsolutePath(),
                    params[3]});
            validation(argsName, params);
            csvReader.handle(argsName);
            String output = params[2].substring(5);
            if (Objects.equals(output, "stdout")) {
                System.out.println(Files.readString(target.toPath()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}