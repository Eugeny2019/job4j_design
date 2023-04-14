package ru.job4j.io;

import java.io.*;

public class Analysis {
    @SuppressWarnings("checkstyle:InnerAssignment")
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            String line;
            boolean isError = false;
            while ((line = reader.readLine()) != null) {
                if (line.matches("[400|500].*") && !isError) {
                    isError = true;
                    writer.write(line.split(" ")[1] + ";");
                }
                if (line.matches("[200|300].*") && isError) {
                    isError = false;
                    writer.write(line.split(" ")[1] + ";" + System.lineSeparator());
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