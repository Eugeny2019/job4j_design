package ru.job4j.io.files;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class FindFile {
    private ArgsName names;

    public static void main(String[] args) throws IOException {
        FindFile findFile = new FindFile();
        findFile.names = ArgsName.of(args);
        findFile.validateArgs();
        findFile.saveIntoFile();
    }

    private void validateArgs() throws IOException {
        File file = new File(names.get("o"));
        file.createNewFile();
        if ("mask".equals(names.get("t"))) {
            names.set("n", names.get("n").replaceAll("\\*", ".*").replaceAll("\\?", "\\\\w?"));
        }
        names.get("d");
    }

    private void saveIntoFile() throws IOException {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(names.get("o")))) {
            Path start = Paths.get(names.get("d"));
            search(start, p -> p.toFile().getName().matches(names.get("n")));
            search(start, p -> p.toFile().getName().matches(names.get("n"))).forEach(path -> printWriter.println(path.toAbsolutePath()));
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
