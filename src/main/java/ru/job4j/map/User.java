package ru.job4j.map;

import java.util.Calendar;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((User) obj).getName())
                && this.children == ((User) obj).getChildren()
                && this.birthday.equals(((User) obj).getBirthday());
    }
}
