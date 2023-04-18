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
}
