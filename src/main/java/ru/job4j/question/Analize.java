package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = previous.size();

        Map<Integer, User> temporaryMap = new HashMap<>();
        for (User user : previous) {
            temporaryMap.put(user.getId(), user);
        }

        for (User user : current) {
            if (temporaryMap.containsKey(user.getId())) {
                if (temporaryMap.get(user.getId()).equals(user)) {
                    deleted--;
                } else {
                    changed++;
                }
            } else {
                added++;
            }
            temporaryMap.put(user.getId(), user);
        }
        deleted -= changed;

        return new Info(added, changed, deleted);
    }

}