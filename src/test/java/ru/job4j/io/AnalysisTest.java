package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnalysisTest {

    //    @Test
//    void checkForTwo400And500A200And300(@TempDir Path tempDir) throws IOException {
    @Test
    void checkForTwo400And500And200And300(@TempDir Path tempDir) throws IOException {

        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(source)))) {
            writer.write("200 10:56:01" + System.lineSeparator() +
                    "500 10:57:01" + System.lineSeparator() +
                    "400 10:58:01" + System.lineSeparator() +
                    "300 10:59:01" + System.lineSeparator() +
                    "500 11:01:02" + System.lineSeparator() +
                    "200 11:02:02");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Analysis fileAanalysis = new Analysis();
        File target = tempDir.resolve("target.csv").toFile();
        fileAanalysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        List<String> result;
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            result = in.lines().toList();
        }
        List<String> correctResult = List.of("10:57:01;10:59:01;, " +
                "11:01:02;11:02:02;");
        assertThat(result.toString()).isEqualTo(correctResult.toString());
    }

    @Test
    void checkFor200And300(@TempDir Path tempDir) throws IOException {

        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(source)))) {
            writer.write("200 10:56:01" + System.lineSeparator() +
                    "300 10:57:01" + System.lineSeparator() +
                    "200 10:58:01");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Analysis fileAanalysis = new Analysis();
        File target = tempDir.resolve("target.csv").toFile();
        fileAanalysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        List<String> result;
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            result = in.lines().toList();
        }
        List<String> correctResult = List.of("");
        assertThat(result.toString()).isEqualTo(correctResult.toString());
    }

    @Test
    void checkForTwo400And500AndOne200And300(@TempDir Path tempDir) throws IOException {

        File source = tempDir.resolve("source.txt").toFile();
        System.out.println(tempDir);

        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(source)))) {
            writer.write("200 10:56:01" + System.lineSeparator() +
                    "500 10:57:01" + System.lineSeparator() +
                    "400 10:58:01" + System.lineSeparator() +
                    "300 10:59:01" + System.lineSeparator() +
                    "200 10:59:01" + System.lineSeparator() +
                    "500 11:01:02");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Analysis fileAanalysis = new Analysis();
        File target = tempDir.resolve("target.csv").toFile();
        fileAanalysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        List<String> result;
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            result = in.lines().toList();
        }
        List<String> correctResult = List.of("10:57:01;10:59:01;, " +
                "11:01:02;");
        assertThat(result.toString()).isEqualTo(correctResult.toString());
    }
}
