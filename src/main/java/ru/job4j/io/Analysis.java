package ru.job4j.io;

import java.io.*;

public class Analysis {
    @SuppressWarnings("checkstyle:InnerAssignment")
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            String line;
            StringBuilder outputString = new StringBuilder();
            boolean mayToWrite = false;
            while ((line = reader.readLine()) != null) {
                if (!mayToWrite) {
                    if (line.contains("400") || line.contains("500")) {
                        mayToWrite = true;
                        outputString.append(line.split(" ")[1]);
                        outputString.append(";");
                    }
                } else {
                    if (line.contains("200") || line.contains("300")) {
                        mayToWrite = false;
                        outputString.append(line.split(" ")[1]);
                        outputString.append(";");
                        writer.write(outputString.toString());
                        writer.write(System.lineSeparator());
                        outputString.delete(0, outputString.length());
                    }
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