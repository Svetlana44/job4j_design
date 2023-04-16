package ru.job4j.serialization.json.model;

import java.util.Arrays;

public class OwnerIdsByOwnerType {
    private final Payment[] payments;
    private final String log;
    private final int id;
    private final Owner owner;
    private final boolean flag;

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