package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new NoSuchElementException("Такого ключа нет");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        valid(args);
        for (String keuValue : args) {
            String[] res = keuValue.split("=");
            res[0] = res[0].substring(1);
            values.put(res[0], res[1]);
        }
    }

    private void valid(String[] arg) {
        for (String string : arg) {
            int evenOne = string.indexOf("=");
            int evenTwo = string.lastIndexOf("=");
            if (!string.startsWith("-") || evenOne != evenTwo
                    || evenOne == 1 || evenOne == string.length() - 1) {
                throw new IllegalArgumentException("Не верный ключ - значение");
            }
        }
    }

}