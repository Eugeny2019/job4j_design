package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Book {
    private final long id;
    private final String name;
    private final Person referral;
    private final String[] authors;

    public Book(long id, String name, Person referral, String[] authors) {
        this.id = id;
        this.name = name;
        this.referral = referral;
        this.authors = authors;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Person getReferral() {
        return referral;
    }

    public String[] getAuthors() {
        return authors;
    }

    public static void main(String[] args) {
        Book book = new Book(15155,
                "Rdkjf fdlkh dskfjhf dfgjkh jdd dkh sdahhjkdfkiywkh fdfhoiuwf'",
                new Person(false, 30, new Contact("11-111"), "Worker", "Married"),
                new String[]{"F.S.Djhsajk", "A.B.Cdsklhdjk"});
        final Gson gson = new GsonBuilder().create();
        String bookInJson = gson.toJson(book);
        System.out.println("Class Book as JSON : \n" + bookInJson);
        System.out.println("Class Book from JSON to class : \n" + gson.fromJson(bookInJson, Book.class));

    }

    @Override
    public String toString() {
        return "Book{"
                + "id=" + id
                + ", name=" + name
                + ", referral=" + referral
                + ", authors=" + authors
                + '}';
    }
}
