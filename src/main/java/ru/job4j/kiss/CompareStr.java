package ru.job4j.kiss;

import java.util.Comparator;

public class CompareStr implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }
}
