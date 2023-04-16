package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final List<FileData> fileDatas = new ArrayList<>(1000);

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileData fileData = new FileData(file);

        for (FileData fd : fileDatas) {
            if (fd.hasSame(fileData.getFileProperty())) {
                System.out.println(fd.toPrintString());
                System.out.println(fileData.toPrintString());
            }
        }
        fileDatas.add(fileData);

        return super.visitFile(file, attrs);
    }
}