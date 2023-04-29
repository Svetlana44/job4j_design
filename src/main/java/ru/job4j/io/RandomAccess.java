/*
"r" - чтения, "rw" - чтение и запись, "rws" - чтение и запись, каждое изменение файла мгновенно отражается в исходном файле.
 Если указанного файла не существует, то в режиме "r" будет выброшено исключение FileNotFoundException,
  "rw" и "rws" будет создан новый файл с указанным названием.
 */
package ru.job4j.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccess {

    public static void main(String[] args) {
        try {
            RandomAccessFile randomAccess = new RandomAccessFile("data/random.txt", "rw");
            randomAccess.writeInt(100);
            randomAccess.writeChar('A');
            randomAccess.writeBoolean(true);
            /* poiter is in end of file   */
            randomAccess.seek(0);

            System.out.println(randomAccess.readInt());
            System.out.println(randomAccess.readChar());
            System.out.println(randomAccess.readBoolean());
            /*  int it is 4 bate  + 2 bite char */
            randomAccess.seek(4);
            System.out.print(randomAccess.readChar() + "->");

            randomAccess.seek(4);
            randomAccess.writeChar('B');
            randomAccess.seek(4);
            System.out.println(randomAccess.readChar());
            System.out.println("getFilePointer: " + randomAccess.getFilePointer());

            randomAccess.seek(randomAccess.length());
            System.out.println("Положение указателя после boolean: " + randomAccess.getFilePointer());
            randomAccess.writeDouble(3.01);
            randomAccess.seek(7);
            System.out.println(randomAccess.readDouble());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}