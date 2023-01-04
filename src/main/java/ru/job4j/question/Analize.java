package ru.job4j.question;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Set<Integer> setIdprevious = new HashSet<>();
        Set<Integer> setIdcurrent = new HashSet<>();
        for (User user : previous) {
            setIdprevious.add(user.getId());
        }
        for (User user : current) {
            setIdcurrent.add(user.getId());
        }

        Info rsl = new Info(0, 0, 0);
        for (Integer i : setIdprevious) {
            if (!setIdcurrent.contains(i)) {
                rsl.setDeleted(rsl.getDeleted() + 1);
            }
        }

        for (Integer i : setIdcurrent) {
            if (!setIdprevious.contains(i)) {
                rsl.setAdded(rsl.getAdded() + 1);
            }
        }


        for (User user : previous) {
            if (current.contains(user)) {
                continue;
            }
            for (User userCurrent : current) {
                if (user.getId() == userCurrent.getId() && !Objects.equals(user.getName(), userCurrent.getName())) {
                    rsl.setChanged(rsl.getChanged() + 1);
                    break;
                }
            }
        }
        return rsl;
    }

}