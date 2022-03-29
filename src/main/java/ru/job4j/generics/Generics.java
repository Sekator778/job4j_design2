package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Он содержит ошибки компиляции, вам необходимо их поправить, закомментировав строки которые их вызывают. При этом:
 *
 * 1-ый метод - работает без ограничений, т.е. в него можно передавать коллекцию, которая хранит любые типы.
 *
 * 2-ой метод - должен иметь ограничение сверху и ограничиваться классом Predator.
 *
 * 3-ий метод - должен иметь ограничение снизу и ограничиваться классом Predator.
 *
 * 4. Загрузите исправленный код в репозиторий оставьте ссылку на коммит.
 *
 * 5. Переведите ответственного на Петра Арсентьева.
 */
public class Generics {
    public static void main(String[] args) {
        Generics gen = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Animal());
        second.add(new Predator());
        third.add(new Tiger());

        gen.printObject(first);
        gen.printObject(second);
        gen.printObject(third);
        System.out.println();

/*       gen.printBoundedWildCard(first); */
        gen.printBoundedWildCard(second);
        gen.printBoundedWildCard(third);
        System.out.println();

        gen.printLowerBoundedWildCard(first);
        gen.printLowerBoundedWildCard(second);
/*       gen.printLowerBoundedWildCard(third); */
    }

    public void printObject(List<?> list) {
        for (Iterator<?> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }
}