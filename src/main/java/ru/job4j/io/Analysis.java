package ru.job4j.io;

import java.io.*;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            String line;
            boolean isError = false;
            while ((line = reader.readLine()) != null) {
                if (line.matches("[45]0{2}\s.*") && !isError || !line.matches("[45]0{2}\s.*") && isError) {
                    isError = !isError;
                    writer.write(line.split(" ")[1] + ";");
                    writer.write((!isError) ? System.lineSeparator() : "");
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