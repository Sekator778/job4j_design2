package ru.job4j.stream;

import java.util.Objects;

public class Student {
    private final int score;
    private final String surname;

    public Student(int score, String surname) {
        this.score = score;
        this.surname = surname;
    }

    public Student(String surname, int score) {
        this.surname = surname;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return score == student.score && Objects.equals(surname, student.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, surname);
    }
}