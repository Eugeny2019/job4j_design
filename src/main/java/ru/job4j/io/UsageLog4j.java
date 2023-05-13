package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        char f = 'f';
        byte b = -12;
        long l = 213123123123123123L;
        float fl = 3.21f;
        double d = 3.123123423432421;
        boolean bl = true;
        LOG.debug("User info name : {}, age : {}, f : {}, b : {}, l : {}, fl : {}, d : {}, bl : {},", name, age, f, b, l, fl, d, bl);
    }
}