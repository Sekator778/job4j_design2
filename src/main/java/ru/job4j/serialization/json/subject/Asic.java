package ru.job4j.serialization.json.subject;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement(name = "asic")
@XmlAccessorType(XmlAccessType.FIELD)
public class Asic {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private Date birthday;
    private int power;
    @XmlElementWrapper(name = "chips")
    @XmlElement(name = "chip")
    private List<Chip> chip;

    public Asic() {
    }

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
