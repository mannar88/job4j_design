package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LogFilter {
    private static List<String> filter(String file) {
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

    private static void save(String log, String file) {
        try (PrintWriter writer  = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            filter(log).forEach(writer::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void print(String name) {
        filter(name).forEach(System.out::println);
    }

    public static void main(String[] args) {
        print("log.txt");
        save("log.txt", "404.txt");
    }
}
