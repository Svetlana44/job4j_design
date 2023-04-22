package ru.job4j.serialization.json.model;

import javax.xml.bind.annotation.XmlAttribute;

public class Owner {
    @XmlAttribute
    private String name;

    public Owner() {
    }

    public Owner(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Owner{"
                + "name='" + name + '\''
                + '}';
    }
    /*гетеры для корректной работы с библиотекой  JSON-Java (org.json) */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
