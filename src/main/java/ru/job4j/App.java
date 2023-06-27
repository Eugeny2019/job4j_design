package ru.job4j;

import ru.job4j.io.ByteArrayStream;
import ru.job4j.io.DataStream;
import ru.job4j.io.objectstream.ObjectStream;
import ru.job4j.question.Analize;
import ru.job4j.question.Info;
import ru.job4j.question.User;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    public static void main(String[] args) {
        String string = "faslkdjfhsadf";

        String st = string.replaceAll("\\\\n", "");
        System.out.println(st);
    }




}
