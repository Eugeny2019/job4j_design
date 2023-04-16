package ru.job4j.io.duplicates;

import java.util.Objects;

public class FileProperty {

    private final long size;
    private final String name;
    private final String absolutePath;
    private boolean isSame = false;

    public FileProperty(long size, String name, String absolutePath) {
        this.size = size;
        this.name = name;
        this.absolutePath = absolutePath;
    }

    public long getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public boolean getSame() {
        return isSame;
    }

    public void setSame(boolean same) {
        isSame = same;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public boolean hasSameName(FileProperty fileProperty) {
        return this.equals(fileProperty);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileProperty that = (FileProperty) o;
        return size == that.size && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, name);
    }
}