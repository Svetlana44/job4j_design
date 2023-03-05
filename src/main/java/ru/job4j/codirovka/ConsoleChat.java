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

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        System.out.println("Input phrase:");
        this.saveLog(new ArrayList<>());
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
        try (Scanner in = new Scanner(System.in);
             BufferedWriter bwriter = new BufferedWriter(new FileWriter(botAnswers))
        ) {
            String phrase;

            while (in.hasNext()) {
                phrase = in.nextLine();

                if (phrase.equals(OUT)) {
                    bwriter.write("закончить" + System.lineSeparator());
                    /*                    System.exit(0); */
                    return;
                }
                if (phrase.equals(STOP)) {
                    bwriter.write("стоп" + System.lineSeparator());
                }
                if (phrase.equals(CONTINUE)) {
                    bwriter.write("продолжить" + System.lineSeparator());
                    Random random = new Random();
                    List<String> answers = readPhrases();
                    int index = random.nextInt(8) + 1;
                    String answer = answers.get(index);
                    bwriter.write(answer + System.lineSeparator());
                    System.out.println(answer);
                    /*                    break; */
                }
                if (!(phrase.contains("стоп") || phrase.contains("продолжить"))) {
                    bwriter.write(phrase + System.lineSeparator());
                }
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