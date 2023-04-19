/*
 Для того чтобы сериализовать и десериализовать нам нужно добавить аннотации JAXB, которая даст библиотеке информацию о том как парсить объект.

1) Rорневой тег @XmlRootElement. Эту аннотацию нужно ставить над сущностью, которая будет корневой.

2) Над вложенными сущностями нам нужно поставить просто @XmlElement

3) Для того чтобы поле считалось атрибутом нужно поставить  @XmlAttribute, по умолчанию поле парсится как тег

4) Мы можем указать также как мы хотим читать/писать объект.
По геттерам/сеттерам или напрямую по полям (используется рефлексия).
Мы будем использовать доступ по полям. Для этих целей служит аннотация @XmlAccessorType
 */
package ru.job4j.serialization.json.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement(name = "ownerIdsByOwnerType")
public class OwnerIdsByOwnerType {
    @XmlElement
    private Payment[] payments;
    @XmlElement
    private String log;
    @XmlElement
    private int id;
    @XmlElement
    private Owner owner;
    @XmlElement
    private boolean flag;

    public OwnerIdsByOwnerType() {
    }

    public OwnerIdsByOwnerType(Payment[] payments, String log, int id, Owner owner, boolean flag) {
        this.payments = payments;
        this.log = log;
        this.id = id;
        this.owner = owner;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "OwnerIdsByOwnerType{"
                + "payments=" + Arrays.toString(payments)
                + ", log='" + log + '\''
                + ", id=" + id
                + ", owner=" + owner
                + ", flag=" + flag
                + '}';
    }
}
/*
{
     "payments":[
     {"id" : 1,
      "ownerType" : "OWNERTYPE",
      "ownerId" : 11,
      "createdAt" : "2023-04-16T18:23:59+03:00"},
     {"id" : 2,
      "ownerType" : "OWNERTYPE",
      "ownerId" : 11,
      "createdAt" : "2023-04-16T18:23:59+03:00"},
     {"id" : 3,
      "ownerType" : "OWNERTYPE",
      "ownerId" : 11,
      "createdAt" : "2023-04-16T18:23:59+03:00"}
      ],
       "log" : "any text",
       "id" : 111,
       "owner": {"name":"Jack"},
       "flag" : true
}


 */