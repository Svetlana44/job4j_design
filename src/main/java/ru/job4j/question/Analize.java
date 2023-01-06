package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> mapPrevious = new HashMap<>();
        Map<Integer, String> mapCurrent = new HashMap<>();
        for (User user : previous) {
            mapPrevious.put(user.getId(), user.getName());
        }
        for (User user : current) {
            mapCurrent.put(user.getId(), user.getName());
        }

        Info rsl = new Info(0, 0, 0);
        for (Map.Entry<Integer, String> entry : mapPrevious.entrySet()) {
            if (!mapCurrent.containsKey(entry.getKey())) {
                rsl.setDeleted(rsl.getDeleted() + 1);
            }
        }

        for (Map.Entry<Integer, String> entry : mapCurrent.entrySet()) {
            if (!mapPrevious.containsKey(entry.getKey())) {
                rsl.setAdded(rsl.getAdded() + 1);
            }
            if (mapPrevious.containsKey(entry.getKey())
                    && !Objects.equals(entry.getValue(), mapPrevious.get(entry.getKey()))) {
                rsl.setChanged(rsl.getChanged() + 1);
            }
        }
        return rsl;
    }

}