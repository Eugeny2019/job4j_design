package ru.job4j.io.duplicates;

import java.nio.file.Path;

public class FileData {
    FileProperty fileProperty;
    String fileAbsolutePath;
    boolean wasShown = false;

    public FileData(Path file) {
        this.fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        this.fileAbsolutePath = file.toFile().getAbsolutePath();
    }

    public boolean hasSame(FileProperty fileProperty) {
        return this.fileProperty.equals(fileProperty);
    }

    public String toPrintString() {
        if (!wasShown) {
            wasShown = true;
            return fileAbsolutePath;
        }
        return "";
    }

    public FileProperty getFileProperty() {
        return fileProperty;
    }
}
