package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JsonSer {
    private boolean bool;
    private int anInt;
    private String string;
    private char[] chars;
    private List<Double> list;

    public JsonSer() {

    }

    public JsonSer(boolean bool, int anInt, String string, char[] chars, List<Double> list) {
        this.bool = bool;
        this.anInt = anInt;
        this.string = string;
        this.chars = chars;
        this.list = list;
    }

    public static void main(String[] args) throws JAXBException, IOException {
        JsonSer jsonSer = new JsonSer(true, 1, "test", new char[]{'a', 'b', 'c'}, List.of(5.0));
        Gson gsonBuilder = new GsonBuilder().create();
        JSONObject jsonObject = new JSONObject(gsonBuilder.toJson(jsonSer));
        System.out.println(jsonObject);
    }

    @Override
    public String toString() {
        return "JsonSer{"
                + "bool=" + bool
                + ", anInt=" + anInt
                + ", string='" + string + '\''
                + ", chars=" + Arrays.toString(chars)
                + ", list=" + list
                + '}';
    }
}
