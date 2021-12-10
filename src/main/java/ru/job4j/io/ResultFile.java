package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    private static String print() {
        String res = "";
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                res = res + i + " * " + j + " = " + (i * j) + ";"
                        + System.lineSeparator();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        try (FileOutputStream outputStream = new FileOutputStream("Calk.txt")) {
            outputStream.write(print().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
