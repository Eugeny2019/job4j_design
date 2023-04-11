package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().map(String::trim)
                    .map(s -> (s.contains("#")) ? s.substring(0, s.indexOf("#")).trim() : s)
                    .filter(this::uncheckedLines)
                    .filter(this::checkMatchesPattern)
                    .forEach(this::parseLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean uncheckedLines(String s) {
        return !(s.isEmpty() || s.startsWith("#"));
    }

    private boolean checkMatchesPattern(String s) {
        if (!s.contains("=") || s.startsWith("=") || s.indexOf("=") == s.length() - 1) {
            throw new IllegalArgumentException("The string '%s' has a pattern 'key=value' violation".formatted(s));
        }
        return true;
    }

    private void parseLines(String s) {
        if (s.isEmpty()) {
            return;
        }
        values.put(s.substring(0, s.indexOf("=")).trim(), s.substring(s.indexOf("=") + 1).trim());
    }

    public String value(String key) {
//        throw new UnsupportedOperationException("Don't impl this method yet!");
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
        read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
            return out.toString();
    }


    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }

}