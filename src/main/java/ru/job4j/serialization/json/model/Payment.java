package ru.job4j.serialization.json.model;

import org.json.JSONPropertyIgnore;

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
    /*гетеры для корректной работы с библиотекой  JSON-Java (org.json) */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}