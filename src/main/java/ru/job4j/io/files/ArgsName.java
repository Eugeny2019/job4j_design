package ru.job4j.io.files;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("""
                    Error: specify an option : -'%s'
                    options:\s
                    \t-d= - directory
                    \t-n= - search stroke
                    \t-t= - type of search: mask, name or regex
                    \t-o= - output file name
                    """, key));
        }
        return values.get(key);
    }

    public void set(String key, String value) {
        values.put(key, value);
    }

    private void parse(String[] args) {
        for (String argument : args) {
            if (!argument.startsWith("-")) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not start with a '-' character", argument));
            }
            if (argument.startsWith("-=")) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a key", argument));
            }
            if (!argument.contains("=")) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain an equal sign", argument));
            }
            if (argument.substring(argument.indexOf("=")).length() == 1) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a value", argument));
            }

            String key = argument.substring(1, argument.indexOf("="));
            String value = argument.substring(argument.indexOf("=") + 1);
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}