package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final List<FileProperty> fileProperties = new ArrayList<>(1000);

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty newFileProperty = new FileProperty(file.toFile().length(),
                                                        file.toFile().getName(),
                                                        file.toFile().getAbsolutePath());

        for (FileProperty fileProperty : fileProperties) {
            if (fileProperty.hasSameName(newFileProperty)) {
                fileProperty.setSame(true);
                newFileProperty.setSame(true);
            }
        }
        fileProperties.add(newFileProperty);

        return super.visitFile(file, attrs);
    }

    public void printSameFiles() {
        fileProperties.stream().filter(FileProperty::getSame).forEach(fileProperty -> System.out.println(fileProperty.getAbsolutePath()));
    }
}