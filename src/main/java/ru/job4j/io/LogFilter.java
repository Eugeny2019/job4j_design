package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            lines = new ArrayList<>(in.lines().toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < lines.size(); i++) {
            String[] tempList = lines.get(i).split(" ");
            if (tempList.length > 1) {
                lines.set(i, tempList[tempList.length - 2]);
            }
        }
        return lines;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        System.out.println(log);

    }
}