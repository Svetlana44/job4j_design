/*В данной программе производится считывание из массива байтов с последующим превращением байтов в символы.*/
package ru.job4j.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteArrayStream {

    public static void main(String[] args) {
        byte[] bytes = new byte[]{'J', 'a', 'v', 'a'};
        /*создаётся объект класса ByteArrayInputStream, представляющий из себя поток для считывания данных и массива байтов.*/
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        int data;
        /*Данные считываются побайтово, каждый считанный байт мы приводим к виду символа и выводим на печать*/
        while ((data = stream.read()) != -1) {
            System.out.print((char) data);
        }
        System.out.println();
        String str = "123456789";
        byte[] bytes1 = str.getBytes();
        /*offset (позиция, с которой начнется считывание) и length (сколько байтов нужно считать)*/
        ByteArrayInputStream byteStream2 = new ByteArrayInputStream(bytes1, 3, 4);
        int data1;
        while ((data1 = byteStream2.read()) != -1) {
            System.out.print((char) data1);
        }
        /*
        происходит запись массива байтов во внутреннее поле объекта outStream. Обратите внимание,
         что вместо метода write() мы используем метод writeBytes().
          Метод write() может выбросить исключение ввода-вывода (IOException),
           а так как в данном случае запись массива во внутреннее поле объекта outStream происходит без участия системы ввода-вывода,
            то в Java 11 в классе ByteArrayOutputStream появился метод writeBytes(),
             который аналогичен методу write(). Он точно так же записывает указанное содержимое,
         только при его использовании не нужно оборачивать этот код в блок try-catch (не бросает исключение).
         */
        System.out.println();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] bytes2 = "Message".getBytes();
        outStream.writeBytes(bytes2);
        System.out.println(outStream);
        /*Считанные данные внутри объекта изменить нельзя. Для чтения и изменения можно вывести (записать) эти данные в новый массив байт.*/
        byte[] byteArray = outStream.toByteArray();
        /*
        И еще один способ - передать записанный массив байт в другой поток. Например, записать в файл.
         Для этого нужно использовать метод writeTo().
        */
        try (FileOutputStream fileOutput = new FileOutputStream("data/message.txt")) {
            outStream.writeTo(fileOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}