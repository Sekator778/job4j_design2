package ru.job4j.serialization.json.subject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SendJson {
    public static void main(String[] args) {
        final Asic asic = new Asic("RusMiner_v1.1");
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(asic));

        final String asicJson =
                "{\"name\":\"RusMiner_v2.1\",\"birthday\":\"Mar 5, 2020, 02:40:11 PM\",\"power\":0,\"chip\":[{\"rate\":10.0,\"temp\":0,\"status\":true,\"model\":\"v10Th\"},{\"rate\":10.0,\"temp\":0,\"status\":true,\"model\":\"v10Th\"}]}\n";
        final Asic asicFromJson = gson.fromJson(asicJson, Asic.class);
        System.out.println(asicFromJson);
    }
}
