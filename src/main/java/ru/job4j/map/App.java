package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        String name = "Dima";
        int children = 0;
        Calendar calendar = new GregorianCalendar(1980, 05, 10);
        User user1 = new User(name, children, calendar);
        User user2 = new User(name, children, calendar);

        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());

        System.out.println(user1 + " " + map.get(user1));
        System.out.println(user2 + " " + map.get(user2));
        System.out.println("user1 equals to user2: " + user1.equals(user2));
    }
}
