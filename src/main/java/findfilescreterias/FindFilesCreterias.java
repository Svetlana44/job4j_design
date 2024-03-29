/*
1. Создать программу для поиска файлов. Все классы, относящиеся к этому заданию должны быть в отдельном пакете
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
7. В программе должна быть валидация ключей и подсказка.
 */
package findfilescreterias;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindFilesCreterias {

    private static void paramsValidation(ArgsName argsName) {

        String parametrd = argsName.get("d");
        String parametrn = argsName.get("n");
        String parametrt = argsName.get("t");
        String parametro = argsName.get("o");

        if (!(Files.isDirectory(Path.of(parametrd)))) {
            throw new IllegalArgumentException("parametr d is NOT valid.");
        }
        System.out.println("d= " + parametrd);

        Pattern pattern = Pattern.compile(".*\\..*");
        Matcher matcher = pattern.matcher(parametrn);
        if (!(matcher.matches())) {
            throw new IllegalArgumentException("parametr n is NOT valid.");
        }
        System.out.println("n= " + parametrn);

        if (!("mask name regex ".contains(parametrt))) {
            throw new IllegalArgumentException("parametr t is NOT valid.");
        }
        System.out.println("t= " + parametrt);

        if (!(Files.isRegularFile(Path.of(parametro)))) {
            throw new IllegalArgumentException("parametr o is NOT valid.");
        }
        System.out.println("o= " + parametro);
    }


    private static void writeIntoFile(List<Path> files, String o) {
        try (FileWriter out = new FileWriter(o)) {
            files.forEach(s -> {
                try {
                    out.write(s.toString() + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Predicate<Path> predicatParam(String searchType, String name) {
        Predicate<Path> predicate = path -> !Objects.equals(path, path);
        if (searchType.equals("mask")) {
            predicate = path -> {
                int index = name.indexOf('.');
                String mask = name.replaceAll("\\.", "[.]").replaceAll("\\*", ".*").replaceAll("\\?", ".");

                return Pattern.compile(mask).matcher(path.getFileName().toString()).matches();
            };
        }
        if (searchType.equals("name")) {
            predicate = path -> (path.getFileName().toString()).equals(name);
        }

        if (searchType.equals("regex")) {
            predicate = path -> Pattern.compile(name).matcher(path.getFileName().toString()).matches();
        }

        return predicate;
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 4) {
            throw new IllegalArgumentException("Количество аргументов должно быть 4.");
        }
        /* parse args into Map<String, String> values*/
        ArgsName argsName = ArgsName.of(args);
        paramsValidation(argsName);
        Predicate<Path> predicate = predicatParam(argsName.get("t"), argsName.get("n"));

        writeIntoFile(Search.search(Path.of(argsName.get("d")), predicate), argsName.get("o"));
    }
}
