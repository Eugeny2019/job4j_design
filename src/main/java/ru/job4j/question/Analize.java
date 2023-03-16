package ru.job4j.question;

import java.util.HashSet;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added;
        int changed;
        int deleted;
        Set<User> temporarySet = new HashSet<>();
        Set<Integer> temporarySetIds = new HashSet<>();

        Set<Integer> previousIds = new HashSet<>();
        for (User user : previous) {
            previousIds.add(user.getId());
        }
        Set<Integer> currentIds = new HashSet<>();
        for (User user : current) {
            currentIds.add(user.getId());
        }

        temporarySetIds.addAll(previousIds);
        temporarySetIds.addAll(currentIds);
        added = temporarySetIds.size() - previousIds.size();

        temporarySetIds.clear();
        temporarySetIds.addAll(currentIds);
        temporarySetIds.addAll(previousIds);
        deleted = temporarySetIds.size() - currentIds.size();

        temporarySet.addAll(previous);
        temporarySet.retainAll(current);
        temporarySetIds.clear();
        temporarySetIds.addAll(previousIds);
        temporarySetIds.retainAll(currentIds);
        changed = temporarySetIds.size() - temporarySet.size();

        return new Info(added, changed, deleted);
    }

}