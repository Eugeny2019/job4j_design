package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private ArgsName argsName;
    public Zip(String... args) {
        argsName = ArgsName.of(args);
        parseArgsNames();
        try {
            List<Path> paths = Search.search(Path.of(argsName.get("d")), p -> p.toFile().getName().endsWith(argsName.get("e")));
            packFiles(paths, new File(argsName.get("o")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void parseArgsNames() {
        if (Objects.isNull(argsName.get("d")) || Objects.isNull(argsName.get("e")) || Objects.isNull(argsName.get("o"))) {
            throw new IllegalArgumentException("There must be three arguments");
        }
        File file = new File(argsName.get("d"));
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
    }

    public void packFiles(List<Path> sources, File target) {
        sources.forEach(file -> packSingleFile(file, target));
    }

    public void packSingleFile(Path source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Zip zip = new Zip(args);
        zip.packSingleFile(
                Path.of("./pom.xml"),
                new File("./pom.zip"));
    }
}