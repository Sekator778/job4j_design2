package ru.job4j.serialization;

import java.io.*;
import java.nio.file.Files;

public class Contact implements Serializable {
    @Serial
    private static final long serialVersionUID = 511475673970270198L;
    private int zipCode;
    private final String phone;
    transient private int amount;
    private final Contain contain = new Contain();

    public Contact(int zipCode, String phone, int amount) {
        this.zipCode = zipCode;
        this.phone = phone;
        this.amount = amount;
    }

    public Contact(String phone) {
        this.phone = phone;
    }

    public int getContain() {
        return contain.containVersion;
    }

    @Override
    public String toString() {
        return "Contact{" + "zipCode=" + zipCode + ", phone='" + phone + '\'' + ", amount='" + amount + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11", 777);

        /* Запись объекта во временный файл, который удалится системой */
        String nameFile = "C:\\Temp\\out.pol";
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(nameFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            System.out.println("Before serialization");
            System.out.println(contact);
            oos.writeObject(contact);
        }
        /* Чтение объекта из файла */
        try (FileInputStream fis = new FileInputStream(nameFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println("After serialization");
            System.out.println(contactFromFile);
            System.out.println(contactFromFile.getContain());
        }
    }

    static class Contain implements Serializable {
        int containVersion = 11;
    }
}
