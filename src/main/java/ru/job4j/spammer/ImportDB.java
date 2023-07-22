package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private final Properties cfg;
    private final String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
             rd.lines().forEach(line -> users.add(checkAndParseStroke(line)));
        }
        return users;
    }

    private User checkAndParseStroke(String stroke) {
        String[] texts = stroke.split(";");
        if (texts.length != 2 || texts[0].length() == 0 || texts[1].length() == 0) {
            throw new IllegalArgumentException();
        }
        return new User(texts[0], texts[1]);
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            try (var statement = cnt.createStatement()) {
                statement.execute("create table if not exists users (name text, email text)");
            }
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("INSERT INTO users values(?, ?)")) {
                    System.out.println(user.name + " : " + user.email);
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "data/dump.txt");
        db.save(db.load());
    }
}