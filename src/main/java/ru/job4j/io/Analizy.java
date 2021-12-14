package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analizy {

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source)); PrintWriter writer = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {

            StringBuilder stringBuilder = new StringBuilder();
            for (String[] arrays : split(read.lines())) {
                if (stringBuilder.length() == 0 && Integer.parseInt(arrays[0]) >= 400) {
                    stringBuilder.append(arrays[1]);
                    stringBuilder.append(";");
                }
                if (stringBuilder.length() > 0 && Integer.parseInt(arrays[0]) < 400) {
                    stringBuilder.append(arrays[1]);
                    stringBuilder.append(";");
                    writer.println(stringBuilder);
                    stringBuilder = new StringBuilder();
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private List<String[]> split(Stream<String> read) {
        return read
                .map(s -> s.trim().split(" "))
                .collect(Collectors.toList());
    }
}