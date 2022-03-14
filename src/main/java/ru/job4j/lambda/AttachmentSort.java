package ru.job4j.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;

public class AttachmentSort {
    public static void main(String[] args) {
        List<Attachment> attachments = Arrays.asList(
                new Attachment("image 1", 100),
                new Attachment("image 3", 13),
                new Attachment("image 2", 34)
        );
        Comparator<Attachment> comparatorSize = new Comparator<Attachment>() {
            @Override
            public int compare(Attachment left, Attachment right) {
                return Integer.compare(left.getSize(), right.getSize());
            }
        };
        attachments.sort(comparatorSize);
        System.out.println(attachments);
        Comparator<Attachment> comparatorName = new Comparator<Attachment>() {
            @Override
            public int compare(Attachment left, Attachment right) {
                return left.getName().compareTo(right.getName());
            }
        };
        attachments.sort(comparatorName);
        System.out.println(attachments);

        BinaryOperator<StringBuilder> builder = StringBuilder::append;
        System.out.println(
                "Строка после объединения: " + builder.apply(
                        new StringBuilder("First string"),
                        new StringBuilder("Second string")
                )
        );

    }
}