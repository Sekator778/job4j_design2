package ru.job4j.serialization.json;

import org.json.JSONPropertyIgnore;

public class A {
    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public String getHello() {
        return "Hello_from_A";
    }

}