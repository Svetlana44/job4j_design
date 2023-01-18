package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("data/even.txt")) {
            StringBuilder sb = new StringBuilder();
            int num;
            while ((num = in.read()) != -1) {
                sb.append((char) num);
            }
            String[] nums = sb.toString().split(System.lineSeparator());
            for (String str : nums) {
                if (Integer.parseInt(str) % 2 == 0) {
                    System.out.println(str + " - чётное");
                } else {
                    System.out.println(str + " - нечётное");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}