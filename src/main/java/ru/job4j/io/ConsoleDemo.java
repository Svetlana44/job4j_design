package ru.job4j.io;

import java.io.Console;
import java.util.Arrays;

public class ConsoleDemo {

    public static void main(String[] args) {
        String login;
        char[] charPassword;
        Console console = System.console();
        if (console == null) {
            System.out.println("Консоль недоступна");
            return;
        }
        login = console.readLine("%s", "Введите логин: ");
        console.printf("Ваш логин: %s\n", login);
        charPassword = console.readPassword("%s", "Введите пароль: ");
        System.out.println("Ваш пароль: " + String.valueOf(charPassword));
        Arrays.fill(charPassword, ' ');
    }

}
/*
В консоли переходим в директорию, где находится наш класс ConsoleDemo, с помощью следующей команды:

cd C:\projects\job4j_design\src\main\java\ru\job4j\io\

Если мы запустим нашу программу обычным способом командой
java ConsoleDemo.java
то увидим, что кириллические символы не отображаются в консоли, так как наш файл имеет кодировку UTF-8, ОС Windows имеет кодировку windows-1251, а в консоли кодировка 866:
Указать кодировку файла в консоли напрямую можно командой:

java -Dfile.encoding=UTF8 ConsoleDemo.java
 */