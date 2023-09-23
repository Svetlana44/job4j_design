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

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindFilesCreterias {

    static Map<String, String> params = new HashMap<>();

    static String d;
    static String n;
    static String t;
    static String o;

    public static boolean paramsValidation(String parametr) {
        if (Objects.equals(parametr, "d")) {
            if (!(Files.isDirectory(Path.of(params.get("d"))))) {
                System.out.println("parametr d is NOT valid.");
                return false;
            }
            System.out.println("d= " + params.get("d"));
        }
        if (Objects.equals(parametr, "n")) {
            Pattern pattern = Pattern.compile(".*\\..*");
            Matcher matcher = pattern.matcher(params.get("n"));
            if (!(matcher.matches())) {
                System.out.println("parametr n is NOT valid.");
                return false;
            }
            System.out.println("n= " + params.get("n"));
        }
        if (Objects.equals(parametr, "t")) {
            if (!("mask name regex ".contains(params.get("t")))) {
                System.out.println("parametr t is NOT valid.");
                return false;
            }
            System.out.println("t= " + params.get("t"));
        }
        if (Objects.equals(parametr, "o")) {
            if (!(Files.isRegularFile(Path.of(params.get("o"))))) {
                System.out.println("parametr o is NOT valid.");
                return false;
            }
            System.out.println("o= " + params.get("o"));
        }
        return true;
    }

    public static void input() {
        System.out.println("Директоря, в которой начинать поиск:");
        Scanner scannerIn = new Scanner(System.in);
        if (scannerIn.hasNextLine()) {
            d = scannerIn.nextLine();
            params.put("d", d);
            if (!paramsValidation("d")) {
                return;
            }
        }
        System.out.println("Имя файла, маска, либо регулярное выражение:");
        if (scannerIn.hasNextLine()) {
            n = scannerIn.nextLine();
            params.put("n", n);
            if (!paramsValidation("n")) {
                return;
            }

        }
        System.out.println("Тип поиска: "
                + System.lineSeparator()
                + "mask искать по маске,"
                + System.lineSeparator()
                + "name по полному совпадение имени, "
                + System.lineSeparator()
                + "regex по регулярному выражению.");
        if (scannerIn.hasNextLine()) {
            t = scannerIn.nextLine();
            params.put("t", t);
            if (!paramsValidation("t")) {
                return;
            }

        }
        if (scannerIn.hasNextLine()) {
            /*        o = "C:\\projects\\job4j_design\\src\\main\\java\\findfilescreterias\\outWithParams.txt";  */
            params.put("o", o);
            if (!paramsValidation("o")) {
                return;
            }

        }

    }

    public static void writeIntoFile(List<Path> files) {
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

    public static void main(String[] args) throws IOException {
        input();
        FindVisitor findVisitor = new FindVisitor(t);
        Files.walkFileTree(Path.of(d), findVisitor);
        writeIntoFile(findVisitor.files);
    }

}
