package ru.job4j.serialization.json;

import org.json.JSONPropertyIgnore;

public class A {
    private B b;

    @JSONPropertyIgnore
    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public String getHello() {
        return String.valueOf(getMult(5));
    }

    public int getMult(int d) {
        int a = d * 5;
        return a;
    }
}