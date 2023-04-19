package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        String[] newArgs = validateArgs(args);

        Path start = Paths.get(newArgs[0]);
        search(start, p -> p.toFile().getName().endsWith(newArgs[1])).forEach(System.out::println);
    }

    private static String[] validateArgs(String[] args) {
        if (args.length == 0 || args[0].length() == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        String[] newArgs = new String[2];
        newArgs[0] = args[0];
        newArgs[1] = (args.length == 1) ?  "java" : args[1];
        return newArgs;
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}