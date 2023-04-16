package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.serialization.json.model.Owner;
import ru.job4j.serialization.json.model.OwnerIdsByOwnerType;
import ru.job4j.serialization.json.model.Payment;

public class Serializer {
    public static void main(String[] args) {
        Payment[] payments = new Payment[3];
        payments[0] = new Payment(1, "OWNER-TYPE", "11", "2023-04-16T18:23:59+03:00");
        payments[1] = new Payment(2, "OWNER-TYPE", "11", "2023-04-16T18:23:59+03:00");
        payments[2] = new Payment(3, "OWNER-TYPE", "11", "2023-04-16T18:23:59+03:00");

        final OwnerIdsByOwnerType ownerIdsByOwnerType =
                new OwnerIdsByOwnerType(payments, "any text", 111, new Owner("Jack"), true);

        final Gson gson = new GsonBuilder().create();
        String toGson = gson.toJson(ownerIdsByOwnerType);
        System.out.println(toGson);

        final String jsonOwnerIdsByOwnerType = "{\"payments\":[{\"id\":1,\"ownerType\":\"OWNER-TYPE\",\"ownerId\":\"11\",\"createdAt\":\"2023-04-16T18:23:59+03:00\"},"
                + "{\"id\":2,\"ownerType\":\"OWNER-TYPE\",\"ownerId\":\"11\",\"createdAt\":\"2023-04-16T18:23:59+03:00\"},"
                + "{\"id\":3,\"ownerType\":\"OWNER-TYPE\",\"ownerId\":\"11\",\"createdAt\":\"2023-04-16T18:23:59+03:00\"}],"
                + "\"log\":\"any text\",\"id\":111,\"owner\":{\"name\":\"Jack\"},\"flag\":true}";

        final OwnerIdsByOwnerType ownerIdsByOwnerTypeMod = gson.fromJson(jsonOwnerIdsByOwnerType, OwnerIdsByOwnerType.class);
        System.out.println(ownerIdsByOwnerTypeMod.toString());
    }
}
/*
OwnerIdsByOwnerType
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
