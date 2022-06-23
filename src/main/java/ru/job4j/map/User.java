package ru.job4j.map;

import java.util.Calendar;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + children * 7 + birthday.hashCode() * 11;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
