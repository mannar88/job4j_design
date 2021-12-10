package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    private static String print() {
        int[][] calk = new int[10][10];
        String res = "";
        for (int i = 1; i < calk.length; i++) {
            for (int j = 1; j < calk[i].length; j++) {
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
