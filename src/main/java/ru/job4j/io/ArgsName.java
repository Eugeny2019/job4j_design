package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String argument : args) {
            if (!argument.contains("-")) {
                throw new IllegalArgumentException("Error: This argument '" + argument + "' does not start with a '-' character");
            }
            if (argument.startsWith("-=")) {
                throw new IllegalArgumentException("Error: This argument '" + argument + "' does not contain a key");
            }
            if (!argument.contains("=")) {
                throw new IllegalArgumentException("Error: This argument '" + argument + "' does not contain an equal sign");
            }
            if (argument.substring(argument.indexOf("=")).length() == 1) {
                throw new IllegalArgumentException("Error: This argument '" + argument + "' does not contain a value");
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

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}