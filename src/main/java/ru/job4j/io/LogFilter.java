package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> lines = new ArrayList<>();

        Predicate<String> predicate = s -> {
            String[] tempList = s.split(" ");
            return tempList.length > 1 && "404".equals(tempList[tempList.length - 2]);
        };

        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            lines = new ArrayList<>(in.lines().filter(predicate).toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        log.forEach(System.out::println);
    }
}