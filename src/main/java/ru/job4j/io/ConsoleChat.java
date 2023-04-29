package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run(List<String> botPhrases) {
        List<String> answersList = new ArrayList<>();
        String answer;
        Scanner sc = new Scanner(System.in);
        boolean canPrintBotMessage = true;
        do {
            System.out.print("user > ");
            sc.hasNextLine();
            answer = sc.nextLine();
            answersList.add("user > " + answer);
            if (STOP.equals(answer) || OUT.equals(answer)) {
                canPrintBotMessage = false;
            }
            if (CONTINUE.equals(answer)) {
                canPrintBotMessage = true;
            }
            if (canPrintBotMessage) {
                String botAnswer = "bot > " + botPhrases.get(new Random().nextInt(botPhrases.size() - 1));
                System.out.println(botAnswer);
                answersList.add(botAnswer);
            }
        } while (!OUT.equals(answer));
        saveLog(answersList);
    }

    private List<String> readPhrases() {
        List<String> botPhrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, Charset.defaultCharset()))) {
            br.lines().forEach(botPhrases::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return botPhrases;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(path, Charset.defaultCharset()))) {
            for (String s : log) {
                br.write(s);
                br.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/correspondence.txt", "./data/botAnswers.txt");
        List<String> botPhrases = cc.readPhrases();
        cc.run(botPhrases);
    }
}