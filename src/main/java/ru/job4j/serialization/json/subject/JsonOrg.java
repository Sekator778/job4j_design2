package ru.job4j.serialization.json.subject;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonOrg {
    final static Asic ASIC = new Asic("UseJsonOrg_v1.1");

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        JSONArray chips = new JSONArray();
        ASIC.getChip().forEach(chip -> {
            chips.put(chip.toString());
        });

        jsonObject.put("name asic", ASIC.getName());
        jsonObject.put("power", "55TH");
        jsonObject.put("chips", chips);

        System.out.println(jsonObject);

        System.out.println(new JSONObject(ASIC));
    }
}
