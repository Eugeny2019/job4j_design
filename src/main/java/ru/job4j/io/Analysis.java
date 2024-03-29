package ru.job4j.io;

import java.io.*;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            String line;
            boolean isError = true;
            while ((line = reader.readLine()) != null) {
                if ((line.startsWith("400") || line.startsWith("500")) == isError) {
                    writer.append(line.split(" ")[1])
                            .append(";")
                            .append((!isError) ? System.lineSeparator() : "");
                    isError = !isError;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}