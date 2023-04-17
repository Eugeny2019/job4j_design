package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> filePropertiesMap = new HashMap<>(1024);

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty newFileProperty = new FileProperty(file.toFile().length(),
                                                        file.toFile().getName());

        filePropertiesMap.putIfAbsent(newFileProperty, new ArrayList<>());
        filePropertiesMap.get(newFileProperty).add(file);

        return super.visitFile(file, attrs);
    }

    public void printSameFiles() {
        filePropertiesMap
                .entrySet()
                .stream()
                .filter(v -> v.getValue().size() > 1)
                .forEach(v -> v.getValue()
                        .forEach(p -> System.out.println(p.toFile().getAbsolutePath())));
    }
}