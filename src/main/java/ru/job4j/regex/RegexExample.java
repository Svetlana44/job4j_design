package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("Я учусь на Job4j");

        String text1 = "Я учусь на Job4j";
        Matcher matcher1 = pattern.matcher(text1);
        boolean isPresent1 = matcher1.matches();
        System.out.println(isPresent1);
        System.out.println(matcher1);

        String text2 = "Я учусь на курсе Job4j";
        Matcher matcher2 = pattern.matcher(text2);
        boolean isPresent2 = matcher2.matches();
        System.out.println(isPresent2);

        Pattern pattern3 = Pattern.compile("Job4j");
        String text3 = "Job4j";
        Matcher matcher3 = pattern3.matcher(text3);
        boolean isPresent3 = matcher3.matches();
        System.out.println(isPresent3);

        String text4 = "job4j";
        Matcher matcher4 = pattern3.matcher(text4);
        System.out.println(matcher4);
        boolean isPresent4 = matcher4.matches();
        System.out.println(isPresent4);

        /*
        CASE_INSENSITIVE, определенной в классе Pattern. Теперь при поиске совпадений регистр букв учитываться не будет
         */
        Pattern pattern5 = Pattern.compile("Job4j", Pattern.CASE_INSENSITIVE);

        String text5 = "Job4j";
        Matcher matcher5 = pattern5.matcher(text5);
        boolean isPresent5 = matcher5.matches();
        System.out.println(isPresent5);

        String text6 = "joB4J";
        Matcher matcher6 = pattern5.matcher(text6);
        System.out.println(matcher6);
        boolean isPresent6 = matcher6.matches();
        System.out.println(isPresent6);

        /*
        шаблон на его присутствие в тексте. Проверка выполняется с помощью метода find() класса Matcher.
         */
        Pattern pattern7 = Pattern.compile("Job4j");
        String text7 = "Я учусь на курсе Job4j";
        Matcher matcher7 = pattern7.matcher(text7);
        boolean isPresent7 = matcher7.find();
        System.out.println(isPresent7);
/* Каждый вызов метода find() начинается с места, где закончился его предыдущий вызов.
Чтобы найти многократные вхождения шаблона в коде, нужно использовать find() в цикле:
 */
        Pattern pattern8 = Pattern.compile("Job4j");
        String text8 = "Job4j и Job4j и Job4j";
        Matcher matcher8 = pattern8.matcher(text8);
        while (matcher8.find()) {
            System.out.println("Найдено совпадение");
        }
/*Получить найденное совпадение в виде строки можно с помощью метода group().
Этот метод выводит ту часть текста, которая совпадает с шаблоном регулярного выражения. В данном случае это "Job4j".*/
        Pattern pattern9 = Pattern.compile("Job4j");
        String text9 = "Job4j1 и Job4j2 и Job4j3";
        Matcher matcher9 = pattern9.matcher(text9);
        while (matcher9.find()) {
            System.out.println("Найдено совпадение: " + matcher9.group());
        }
/*Метод start() - получает индекс, в котором находится первый символ искомой последовательности символов.
Метод end() - получает индекс, следующий за последним символов искомой последовательности символов.*/
        Pattern pattern10 = Pattern.compile("Job4j");
        String text10 = "Job4j1 и Job4j2 и Job4j3";
        Matcher matcher10 = pattern10.matcher(text10);
        while (matcher10.find()) {
            System.out.println("Найдено совпадение. iStart: " + matcher10.start()
                    + " iEnd: " + matcher10.end());
        }
        /* Найденные совпадения можно заменить другой строкой с помощью метода replaceAll() */

        Pattern pattern11 = Pattern.compile("123");
        String text11 = "1231 и 1232 и 1233";
        Matcher matcher11 = pattern11.matcher(text11);
        String rsl = matcher11.replaceAll("Job4j");
        System.out.println(rsl);
    }
}