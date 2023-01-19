/*
Метод filter должен прочитать файл и вернуть строки, где предпоследнее значение - это 404.

Например строка,

0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:23 +0300] "GET /job4j.ru/profile/ HTTP/1.1" 404 1110
 */

package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader bufReader = new BufferedReader(new FileReader(file))) {
            rsl = bufReader.lines().filter(str -> str.contains("\" 404")).collect(Collectors.toList());
/*
собрать из потока строк список
rsl = bufReader.lines().collect(Collectors.toList());
*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rsl;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        log.forEach(str -> System.out.println(log));

    }
}