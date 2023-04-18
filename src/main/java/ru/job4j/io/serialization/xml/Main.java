package ru.job4j.io.serialization.xml;

import ru.job4j.serialization.json.model.Owner;
import ru.job4j.serialization.json.model.OwnerIdsByOwnerType;
import ru.job4j.serialization.json.model.Payment;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        Payment[] payments = new Payment[3];
        payments[0] = new Payment(1, "OWNER-TYPE", "11", "2023-04-16T18:23:59+03:00");
        payments[1] = new Payment(2, "OWNER-TYPE", "11", "2023-04-16T18:23:59+03:00");
        payments[2] = new Payment(3, "OWNER-TYPE", "11", "2023-04-16T18:23:59+03:00");

        OwnerIdsByOwnerType ownerIdsByOwnerType =
                new OwnerIdsByOwnerType(payments, "any text", 111, new Owner("Jack"), true);
        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(OwnerIdsByOwnerType.class);
        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(ownerIdsByOwnerType, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            OwnerIdsByOwnerType result = (OwnerIdsByOwnerType) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }
}