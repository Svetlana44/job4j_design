/*
- пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла и выводит в ответ.
- программа замолкает если пользователь вводит слово «стоп», при этом он может продолжать отправлять сообщения в чат.
- если пользователь вводит слово «продолжить», программа снова начинает отвечать.
- при вводе слова «закончить» программа прекращает работу.
- запись диалога, включая слова-команды стоп/продолжить/закончить должны быть записаны в текстовый лог.
- текстовые файлы с ответами бота и лог размещайте в ранее созданном каталоге data в корне проекта.
 */
package ru.job4j.codirovka;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private List<String> answersList;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        System.out.println("Input phrase:");
        answersList = readPhrases();
        List<String> log = new ArrayList<>();
        try (Scanner in = new Scanner(System.in)) {
            String phrase;
            while (in.hasNext()) {
                phrase = in.nextLine();

                if (OUT.equals(phrase)) {
                    System.out.println(phrase);
                    log.add(phrase);
                    saveLog(log);
                    return;
                }
                if (STOP.equals(phrase)) {
                    System.out.println(phrase);
                    log.add(phrase);
                    saveLog(log);
                }
                if (CONTINUE.equals(phrase)) {
                    System.out.println(phrase);
                    log.add(phrase);
                    Random random = new Random();
                    int index = random.nextInt(answersList.size());
                    String botAnswers = answersList.get(index);
                    log.add(botAnswers);
                    System.out.println(botAnswers);
                    saveLog(log);
                }
                if (!(STOP.equals(phrase) || CONTINUE.equals(phrase))) {
                    log.add(phrase);
                }
            }
            this.saveLog(log);
        }
    }

    private List<String> readPhrases() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                answers.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }

    private void saveLog(List<String> log) {
        try (PrintStream bwriter = new PrintStream(new FileOutputStream(botAnswers))) {
            for (String str : log) {
                bwriter.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/answers.txt", "./data/logAnswers.txt");
        cc.run();
    }
}