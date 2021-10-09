package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.Contact;

import java.util.List;

public class TestJsonOrg {
    public static void main(String[] args) {
        /* JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+38(067111255\"}");
        /* JSONArray из ArrayList */
        JSONArray jsonStatuses = new JSONArray(List.of("Student", "Free"));
        /* JSONObject напрямую методом put */
        final Person person = new Person(false, 43, new Contact("111-222"), "Worker", "Married");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.isSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);
        /* Выведем результат в консоль */
        System.out.println(jsonObject);
        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(person));
    }

}
