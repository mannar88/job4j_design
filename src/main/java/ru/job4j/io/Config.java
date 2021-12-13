package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Config {
    private final String path;
    private Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            values = reader.lines()
                    .filter(s -> !s.isBlank() && !s.trim().startsWith("#"))
                    .map(this::check)
                    .collect(Collectors.toMap(strings -> strings[0].trim(), strings -> strings[1].trim()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        if (values.isEmpty()) {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
        return values.get(key);
    }

    private String[] check(String line) {
        line = line.trim();
        int index = line.indexOf("=");
        if (index < 1 || index == line.length() - 1 || index != line.lastIndexOf("=")) {
            throw new IllegalArgumentException();
        }
        return line.split("=");
    }

}