package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.json.model.Owner;
import ru.job4j.serialization.json.model.OwnerIdsByOwnerType;
import ru.job4j.serialization.json.model.Payment;

/*
JSON-Java (org.json).
 */
public class JSONJava {


    public static void main(String[] args) {
        Payment[] payments = new Payment[3];
        payments[0] = new Payment(1, "OWNER-TYPE", "11", "2023-04-16T18:23:59+03:00");
        payments[1] = new Payment(2, "OWNER-TYPE", "11", "2023-04-16T18:23:59+03:00");
        payments[2] = new Payment(3, "OWNER-TYPE", "11", "2023-04-16T18:23:59+03:00");

        JSONArray jsonArrayPayments = new JSONArray(payments);

        OwnerIdsByOwnerType ownerIdsByOwnerType =
                new OwnerIdsByOwnerType(payments, "any text", 111, new Owner("Jack"), true);

        JSONObject jsonObjectOwnerIdsByOwnerType = new JSONObject(ownerIdsByOwnerType);

        JSONObject jsonObjectPayment1 = new JSONObject("{"
                + "\"id\" : 1,"
                + "\"ownerType\" : \"OWNERTYPE\","
                + "\"ownerId\" : 11,"
                + "\"createdAt\" : \"2023-04-16T18:23:59+03:00\"}");
        JSONObject jsonObjectPayment2 = new JSONObject("{"
                + "\"id\" : 2,"
                + "\"ownerType\" : \"OWNERTYPE\","
                + "\"ownerId\" : 11,"
                + "\"createdAt\" : \"2023-04-16T18:23:59+03:00\"}");
        JSONObject jsonObjectPayment3 = new JSONObject("{"
                + "\"id\" : 3,"
                + "\"ownerType\" : \"OWNERTYPE\","
                + "\"ownerId\" : 11,"
                + "\"createdAt\" : \"2023-04-16T18:23:59+03:00\"}");

        JSONArray jsonArrayPayments2 = new JSONArray();
        jsonArrayPayments2.put(jsonObjectPayment1);
        jsonArrayPayments2.put(jsonObjectPayment2);
        jsonArrayPayments2.put(jsonObjectPayment3);

        JSONObject jsonObjectLog = new JSONObject("{\"log\" : \"any text\"}");
        JSONObject jsonObjectId = new JSONObject("{\"id\" : 111}");
        JSONObject jsonObjectOwner = new JSONObject("{\"owner\": {\"name\":\"Jack\"}}");
        JSONObject jsonObjectFlag = new JSONObject("{\"flag\" : true}");
        JSONObject jsonObjectOwnerIdsByOwnerType2 = new JSONObject(jsonArrayPayments2);
        jsonObjectOwnerIdsByOwnerType2.put("log", jsonObjectLog);
        jsonObjectOwnerIdsByOwnerType2.put("id", jsonObjectId);
        jsonObjectOwnerIdsByOwnerType2.put("owner", jsonObjectOwner);
        jsonObjectOwnerIdsByOwnerType2.put("flag", jsonObjectFlag);

        System.out.println(jsonObjectOwnerIdsByOwnerType + System.lineSeparator());
        System.out.println(jsonObjectOwnerIdsByOwnerType2 + System.lineSeparator());

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
}
