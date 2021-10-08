package ru.job4j.serialization.json.subject;

import java.util.*;

public class Asic {
    private String name;
    private Date birthday;
    private int power;
    private List<Chip> chip;

    public Asic(String name) {
        this.name = name;
        this.birthday = getBirthday();
        setChips();
    }

    private Date getBirthday() {
        Calendar calendar = new GregorianCalendar(2020, Calendar.MARCH, 5);
        return calendar.getTime();
    }

    public void setChips() {
        chip = List.of(Chip.v10Th(), Chip.v30Th());
    }
    @Override
    public String toString() {
        return "Asic{"
                + "name=" + name
                + ", birthday=" + birthday
                + ", power=" + power
                + ", chips=" + chip.toString()
                + '}';
    }

}
