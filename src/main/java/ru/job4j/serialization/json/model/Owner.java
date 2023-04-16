package ru.job4j.serialization.json.model;

public class Owner {
    private final String name;

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
