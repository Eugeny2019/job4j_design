package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
     public void packSingleFile(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);

        Zip zip = new Zip();
        try {
            List<Path> paths = Search.search(Path.of(argsName.get("d")), p -> !p.toFile().getName().endsWith(argsName.get("e")));
            List<File> files = new ArrayList<>();
            paths.forEach(p -> files.add(p.toFile()));
            zip.packSingleFile(files, new File(argsName.get("o")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void validate(ArgsName argsName) {
        if (Objects.isNull(argsName.get("d")) || Objects.isNull(argsName.get("e")) || Objects.isNull(argsName.get("o"))) {
            throw new IllegalArgumentException("There must be three arguments");
        }
        File file = new File(argsName.get("d"));
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
    }
}