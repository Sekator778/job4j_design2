package ru.job4j.stream;

import java.util.stream.Stream;

public class Card {
    private final Suit suit;
    private final Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    @Override
    public String toString() {
        return suit.name() + " : " + value.name();
    }

    public static void main(String[] args) {
        Stream.of(Suit.values())
                .flatMap(value -> Stream.of(Value.values())
                .map(card -> new Card(value, card)))
                .forEach(System.out::println);
    }
}