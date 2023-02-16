package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class AttributesUsage {
    public static void main(String[] args) throws IOException {
        Path file = Path.of("Attributes.txt");
        /* создаёт файл Attributes.txt в корне проекта */
        /*  его нужно перед созданием удалять, иначе, будет исключение */
        Files.createFile(file);
        /*  readAttributes() получаем атрибуты файла
        * Вторым параметром здесь передаётся тип класса атрибутов, который будет инкапсулирован в объект интерфейса BasicFileAttributes. */
        BasicFileAttributes attributes = Files.readAttributes(file, BasicFileAttributes.class);
        System.out.println("Это обычный файл? " + attributes.isRegularFile());
        System.out.println("Это директория? " + attributes.isDirectory());
        System.out.println("Это символическая ссылка? " + attributes.isSymbolicLink());
        System.out.println("Это не файл, директория или символическая ссылка? " + attributes.isOther());
        System.out.println("Дата создания файла: " + attributes.creationTime());
        System.out.println("Размер файла: " + attributes.size());
        System.out.println("Время последнего доступа: " + attributes.lastAccessTime());
        System.out.println("Время последнего изменения: " + attributes.lastModifiedTime());
        /* какой класс в действительности инкапсулирован в переменную attributes */
        System.out.println(attributes.getClass());
    }
}