/*
Бараш терпеть не может нули между другими цифрами в числе. По его мнению они лишь портят математическую красоту числа и гармонию между цифрами. Поэтому когда он видит число — он тут же хочет избавить его от любого присутствия таких нулей! При этом его не смущают нули в конце числа, ведь они не стоят между двумя другими отличными от нуля цифрами. Разумеется, ведущие нули его тоже не беспокоят.

И вот в очередной раз, когда Бараш навещал Пина, он увидел на доске с чертежами и схемами число X. Единственное, что он смог сделать — это подсчитать количество нулей, которые окружены другими цифрами в числе.

Подсчитайте и вы! Например, в числе 100500100500 таких нулей два, а в 5304000453040004 всего четыре.

Формат входных данных

Входные данные содержат одно число k (1 \leq k \leq 10^9)(1≤k≤10
9
 ).
Ввод:
100500
вывод:
2

Формат выходных данных

Выведите одно число — количество нулей, которые не нравятся Барашу.
* */
package tinkoff.qa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zero {

    public static void main(String[] args) {

        int result = 0;
        int k = 0;
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            k = scanner.nextInt();
        }
        String str = String.valueOf(k);

        String regex = "[1-9]0\\d[1-9]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        List<String> strings = new ArrayList<>();

        int count = 0;
        while (matcher.find()) {
            strings.add(matcher.group());
        }
        for (String s : strings) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '0') {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}