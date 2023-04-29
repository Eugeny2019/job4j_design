package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("There must be three arguments");
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);

        Zip zip = new Zip();
        try {
            List<Path> paths = Search.search(Path.of(argsName.get("d")), p -> !p.toFile().getName().endsWith(argsName.get("e")));
            zip.packFiles(paths, new File(argsName.get("o")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void validate(ArgsName argsName) {
        argsName.get("e");
        argsName.get("o");
        File file = new File(argsName.get("d"));
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
    }
}