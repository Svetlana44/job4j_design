package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Multiplication {
    static public void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/multyTable.txt")) {
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 10; j++) {
                    String multiply = Integer.toString(i * j);
                    if (multiply.length() < 2) {
                        multiply = multiply + " ";
                    }
                    multiply = multiply + " ";
                    out.write(multiply.getBytes(StandardCharsets.UTF_8));
                }
                out.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
