package ru.job4j.list;

import java.util.List;

/**
 * Задание: метод принимает список и элемент который мы ищем в списке.
 * Необходимо определить, является ли этот элемент уникальным в этом списке.
 * Для это необходимо найти первый и последний индекс вхождения элемента.
 * Если равны - значит элемент уникальный.
 * Необходимо предусмотреть ситуацию, что такого элемента нет вообще в коллекции,
 * в этом случае также нужно вернуть false.
 */
public class UniqueElement {
    public static boolean checkList(List<String> list, String str) {
        int index = 0;
        int first = 0;
        int last = 0;
        boolean result = false;
        for (String s : list) {
            index++;
            if (s.equals(str) && first == 0) {
                first = index;
                last = index;
            } else if (s.equals(str)) {
                last = index;
            }
        }
        if (first == last && last != 0) {
            result = true;
        }
        return result;
    }
}