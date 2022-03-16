package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<Person> nameTest = person -> person.getName().contains(key);
        Predicate<Person> surnameNameTest = person -> person.getSurname().contains(key);
        Predicate<Person> phoneTest = person -> person.getPhone().contains(key);
        Predicate<Person> addressTest = person -> person.getAddress().contains(key);
        Predicate<Person> combine = nameTest.or(surnameNameTest
                        .or(phoneTest
                        .or(addressTest)));

        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}