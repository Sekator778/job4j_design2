package ru.job4j.serialization.json.subject;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "chip")
public class Chip {
    @XmlAttribute
    private double rate;
    @XmlAttribute
    private int temp;
    @XmlAttribute
    private boolean status;
    @XmlAttribute
    private String model;

    public Chip() {
    }

    public Chip(double rate, int temp, boolean status, String model) {
        this.rate = rate;
        this.temp = temp;
        this.status = status;
        this.model = model;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Chip model: " + model + " status: " + status + " have rate: " + rate + "Th " + " temperature: " + temp + " \u2109";
    }
    public static Chip v30Th() {
        return new Chip(30.5, 0, true, "v30.5Th");
    }
    public static Chip v10Th() {
        return new Chip(10.0, 0, true, "v10Th");
    }
}
