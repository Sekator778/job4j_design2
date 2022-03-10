package ru.job4j.ex.initialization;

public class Animal {
    public static void main(String[] args) {
        new Cat();
    }

    private static String str1 = "Статическая переменная Animal";
    private String str2 = "Нестатическая переменная Animal";

    public Animal() {
        System.out.println("Конструктор Animal");
    }

    {
        System.out.println(str2);
        System.out.println("...Нестатические переменные Animal проинициализированы... \n" + "Нестатический блок Animal");
    }

    static {
        System.out.println(str1);
        System.out.println("...Статические переменные Animal проинициализированы... \n" + "Статический блок Animal");
    }

    public static class Cat extends Animal {
        private static String str3 = "Статическая переменная Cat";
        private String str4 = "Нестатическая переменная Cat";

        public Cat() {
            System.out.println("Конструктор Cat отработал");
        }

        {
            System.out.println(str4);
            System.out.println("...Нестатические переменные Cat проинициализированы... \n" + "Нестатический блок Cat");
        }

        static {
            System.out.println(str3);
            System.out.println("...Статические переменные Cat проинициализированы... \n" + "Статический блок Cat");
        }
    }
}