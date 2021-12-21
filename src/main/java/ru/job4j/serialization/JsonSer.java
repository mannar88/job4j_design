package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class JsonSer {
    private final boolean bool = true;
    private final int anInt = 1;
    private final String string = "test";
    private final char[] chars = {'a', 'b', 'c'};
    private final List<Double> list = List.of(5.0);

    public static void main(String[] args) {
        JsonSer jsonSer = new JsonSer();
        Gson gson = new GsonBuilder().create();
        String gsonString = gson.toJson(jsonSer);
        JsonSer jsonSer1 = gson.fromJson(gsonString, JsonSer.class);
        System.out.println(jsonSer1.equals(jsonSer));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JsonSer jsonSer = (JsonSer) o;
        return bool == jsonSer.bool && anInt == jsonSer.anInt && Objects.equals(string, jsonSer.string) && Arrays.equals(chars, jsonSer.chars) && Objects.equals(list, jsonSer.list);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(bool, anInt, string, list);
        result = 31 * result + Arrays.hashCode(chars);
        return result;
    }
}
