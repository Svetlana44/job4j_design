/*
1. Реализуйте метод unavailable().
    source - путь к файлу лога.
    target - имя путь к файлу результата анализа.
2. Метод unavailable() должен находить диапазоны, когда сервер не работал.
    Сервер не работал, если status = 400 или 500.
    Диапазоном считается последовательность статусов 400 и 500
3. Результат анализа нужно записать в файл target.
    Формат файла
начала периода;конец периода;
10:57:01;11:02:02;
 */

package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
/*
    Try with multiple Resource
 */
        try (BufferedReader inReadFile = new BufferedReader(new FileReader(source));
             BufferedWriter outWriteFile = new BufferedWriter(new FileWriter(target))) {
            boolean flag = false;
            String line;
            while ((line = inReadFile.readLine()) != null) {
                if ((line.startsWith("4") || line.startsWith("5")) && !flag) {
                    flag = true;
                    outWriteFile.write(line.substring(4) + ";");
                }
                if (!(line.startsWith("4") || line.startsWith("5")) && flag) {
                    flag = false;
                    outWriteFile.write(line.substring(4) + ";" + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}