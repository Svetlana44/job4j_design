/* input/output
3. Поиск файлов по критерию [#783 # [#783]]
* 1. Создать программу для поиска файлов. Все классы, относящиеся к этому заданию должны быть в отдельном пакете
Важно! Допускается использование ранее созданных вами классов.
2. Программа должна искать данные в заданном каталоге и подкаталогах.
3. Имя файла может задаваться, целиком, по маске, по регулярному выражению(не обязательно).
4. Программа должна запускаться с параметрами, например:  -d=c:  -n=*.?xt -t=mask -o=log.txt
Ключи
-d - директория, в которой начинать поиск.
-n - имя файла, маска, либо регулярное выражение.
-t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
-o - результат записать в файл.
5. Параметры в программу должны передаваться в командной строке.
6. Программа должна записывать результат в файл.
7. В программе должна быть валидация ключей и подсказка.*/
package ru.job4j.io.find.files;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FindFiles {

    Map<String, String> params = new HashMap<>();

    static String[] consolArgs;

    public static void main(String[] args) {
        String temp;
        String[] tempKeyValue;
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Ведите параметры:");
            temp = in.nextLine();

        }
        consolArgs = temp.split(" ");
        Arrays.stream(consolArgs).forEach(str -> System.out.println(str));

        String[] consolArgsNoEmpty = (String[]) Arrays.stream(consolArgs).filter(s -> !s.isEmpty()).toArray();

        /*тут нужно добавиь метод валидации и потом кидать в мапу*/
        Arrays.stream(consolArgsNoEmpty).forEach(str -> str.split("="));
    }
}
