package ru.job4j.serialization.json.model;

import javax.xml.bind.annotation.XmlAttribute;

public class Payment {
    @XmlAttribute
    private int id;
    @XmlAttribute
    private String ownerType;
    @XmlAttribute
    private String ownerId;
    @XmlAttribute
    private String createdAt;

    public Payment() {
    }

    public Payment(int id, String ownerType, String ownerId, String createdAt) {
        this.id = id;
        this.ownerType = ownerType;
        this.ownerId = ownerId;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Payment{"
                + "id=" + id
                + ", ownerType='" + ownerType + '\''
                + ", ownerId='" + ownerId + '\''
                + ", createdAt='" + createdAt + '\''
                + '}';
    }
}