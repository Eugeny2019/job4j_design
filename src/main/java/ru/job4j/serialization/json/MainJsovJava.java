package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainJsovJava {
    public static void main(String[] args) {

        /* JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Book book = new Book(15155,
                "Rdkjf fdlkh dskfjhf dfgjkh jdd dkh sdahhjkdfkiywkh fdfhoiuwf'",
                new Person(false, 40, new Contact("11-111"), "Worker", "Married"),
                new String[]{"F.S.Djhsajk", "A.B.Cdsklhdjk"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", book.getId());
        jsonObject.put("name", book.getName());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(book).toString());
    }
}
