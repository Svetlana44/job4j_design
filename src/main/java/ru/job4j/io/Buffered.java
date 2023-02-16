/*
производится чтение содержимого файла input.txt и его запись в файл output.txt
Буферизированные обёртки реализуют шаблон декоратор
 */
package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Buffered {
    public static void main(String[] args) {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("data/input.txt"));
                /*  new FileOutputStream("data/output.txt");    */
                /*  * Чтобы метод write() добавлял данные в файл, а не перезаписывал, нужно использовать такой конструктор:
                 *  new FileOutputStream("data/output.txt", true)  */
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("data/output.txt", true))) {
            /*  readAllBytes() будет происходить чтение в буфер чтения и передача данных в буфер записи, откуда будет производиться запись в файл */
            out.write(in.readAllBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}