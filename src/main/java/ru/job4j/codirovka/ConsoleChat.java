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
import java.nio.file.Files;
import java.nio.file.Paths;
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

                if (phrase.equals(OUT)) {
                    log.add("закончить");
                    return;
                }
                if (phrase.equals(STOP)) {
                    log.add("стоп");
                }
                if (phrase.equals(CONTINUE)) {
                    log.add("продолжить");
                    Random random = new Random();
                    int coutStr = countString();
                    int index = random.nextInt(coutStr) + 1;
                    String botAnswers = answersList.get(index);
                    log.add(botAnswers);
                    System.out.println(botAnswers);
                }
                if (!(phrase.contains("стоп") || phrase.contains("продолжить"))) {
                    log.add(phrase);
                }
            }
            this.saveLog(log);
        }
    }

    private List<String> readPhrases() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.path))) {
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
        try (BufferedWriter bwriter = new BufferedWriter(new FileWriter(botAnswers))) {
            for (String str : log) {
                bwriter.write(str + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* найти количество строк в файле */
    private int countString() {
        List<String> fileStream = null;
        try {
            fileStream = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileStream.size();
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/answers.txt", "./data/logAnswers.txt");
        cc.run();
    }
}