/*
 Для того чтобы сериализовать и десериализовать нам нужно добавить аннотации JAXB, которая даст библиотеке информацию о том как парсить объект.

1) Rорневой тег @XmlRootElement. Эту аннотацию нужно ставить над сущностью, которая будет корневой.

2) Над вложенными сущностями нам нужно поставить просто @XmlElement

3) Для того чтобы поле считалось атрибутом нужно поставить  @XmlAttribute, по умолчанию поле парсится как тег

4) Мы можем указать также как мы хотим читать/писать объект.
По геттерам/сеттерам или напрямую по полям (используется рефлексия).
Мы будем использовать доступ по полям. Для этих целей служит аннотация @XmlAccessorType
 */
package ru.job4j.serialization.java;

import java.io.*;
import java.nio.file.Files;

public class Contact implements Serializable {
    private static long serialVersionUID = 1L;
    private int zipCode;
    private String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone + '\''
                + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");

        /* Запись объекта во временный файл, который удалится системой */
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos =
                     new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }

        /* Чтение объекта из файла */
        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois =
                     new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
        }
    }
}