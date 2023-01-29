/*
удаление из файла запрещенных слов
Сначала в файле pom.xml в блок зависимостей <dependencies>...</dependencies> добавим зависимость
<dependency>
     <groupId>org.junit.jupiter</groupId>
     <artifactId>junit-jupiter-params</artifactId>
     <version>5.8.2</version>
     <scope>test</scope>
</dependency>
 */
package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

public class Abuse {

    public static void drop(String source, String target, List<String> words) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
                /*PrintWriter символьное представление (new BufferedOutputStream  буферизация ( по байтам new FileOutputStream(target)*/
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            in.lines()
                    /*  \\s+   one  or   more  whitespaces  */
                    .flatMap(line -> Stream.of(line.split("\\s+")))
                    .filter(word -> !words.contains(word)).map(word -> word + " ")
                    .forEach(out::print);
        }
    }
}