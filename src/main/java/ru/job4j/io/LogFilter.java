package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> res = null;
        Predicate<String> predicate = s -> s.startsWith("404", s.lastIndexOf(" ") - 3);
        try (BufferedReader read = new BufferedReader(new FileReader(file))) {
            res = read.lines()
                    .filter(predicate)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}
