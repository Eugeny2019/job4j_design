package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;


public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        try (Scanner sc = new Scanner(new FileInputStream(argsName.get("path")))) {
            sc.useLocale(Locale.getDefault());
            sc.useDelimiter(System.lineSeparator());
            if (!sc.hasNextLine()) {
                throw new NoSuchElementException(String.format("There is no headlines in %s", argsName.get("path")));
            }

            String[] filter = argsName.get("filter").split(",");
            String[] headlines = sc.nextLine().split(argsName.get("delimiter"));

            int numberOfMatches = 0;
            for (String s : filter) {
                for (String headline : headlines) {
                    if (s.equals(headline)) {
                        numberOfMatches++;
                    }
                }
            }
            if (numberOfMatches != filter.length) {
                throw new NoSuchElementException(String.format("Not all colunms from %s are present in %s", argsName.get("filter"), argsName.get("path")));
            }
            if (!sc.hasNextLine()) {
                throw new NoSuchElementException(String.format("There is no data in %s", argsName.get("path")));
            }
            List<ArrayList<String>> data = new ArrayList<>();
            for (int i = 0; i < filter.length; i++) {
                data.add(new ArrayList<>());
            }
            while (sc.hasNextLine()) {
                String[] datas = sc.nextLine().split(argsName.get("delimiter"));
                for (int i = 0; i < filter.length; i++) {
                    for (int j = 0; j < headlines.length; j++) {
                        if (headlines[j].equals(filter[i])) {
                            data.get(i).add(datas[j]);
                        }
                    }
                }
            }

            try (PrintStream outStream = ("stdout".equals(argsName.get("out")))
                    ? System.out : new PrintStream(new FileOutputStream(argsName.get("out")))) {
                for (int i = 0; i < filter.length; i++) {
                    outStream.printf("%s", filter[i]);
                    if (i < filter.length - 1) {
                        outStream.print(argsName.get("delimiter"));
                    }
                }
                outStream.print(System.lineSeparator());
                for (int j = 0; j < data.get(0).size(); j++) {
                    for (int k = 0; k < data.size(); k++) {
                        outStream.printf("%s", data.get(k).get(j));
                        if (k < data.size() - 1) {
                            outStream.print(argsName.get("delimiter"));
                        }
                    }
                    outStream.print(System.lineSeparator());
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("There must be four arguments");
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        handle(argsName);
    }

    private static void validate(ArgsName argsName) {
        if (!argsName.get("path").toLowerCase().endsWith(".csv")) {
            throw new IllegalArgumentException(String.format("Argument path must ends with .csv: %s", argsName.get("path")));
        }
        File fileInPath = new File(argsName.get("path"));
        if (!(fileInPath.exists() || fileInPath.isFile())) {
            throw new IllegalArgumentException(String.format("File %s not found or is not a file.", argsName.get("path")));
        }
        argsName.get("delimiter");
        argsName.get("out");
        argsName.get("filter");
    }
}