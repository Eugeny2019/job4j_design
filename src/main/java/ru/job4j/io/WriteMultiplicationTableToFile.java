package ru.job4j.io;

import java.io.FileOutputStream;

public class WriteMultiplicationTableToFile {
    public static void main(String[] args) {
        StringBuilder stringToOut = new StringBuilder();
        try (FileOutputStream out = new FileOutputStream("data/multiplication_table.txt")) {
            for (int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 10; j++) {
                    stringToOut.append(i * j);
                    stringToOut.append("\t");
                }
                out.write(stringToOut.toString().getBytes());
                out.write(System.lineSeparator().getBytes());
                stringToOut.delete(0, stringToOut.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
