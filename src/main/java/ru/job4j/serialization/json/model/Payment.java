package ru.job4j.serialization.json.model;

public class Payment {
    private final int id;
    private final String ownerType;
    private final String ownerId;
    private final String createdAt;

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