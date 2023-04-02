package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> resultLines = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            lines = new ArrayList<>(in.lines().toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String line : lines) {
            String[] tempList = line.split(" ");
            if (tempList.length > 1 && tempList[tempList.length - 2].equals("404")) {
                resultLines.add(line);
            }
        }
        return resultLines;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        System.out.println(log);

    }
}