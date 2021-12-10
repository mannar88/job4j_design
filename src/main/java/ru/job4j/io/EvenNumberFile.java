package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream("input.txt")) {

            StringBuilder text = new StringBuilder();
            int read;
            while ((read = inputStream.read()) != -1) {
                text.append((char) read);
            }
            String[] line = text.toString().split(System.lineSeparator());
            for (String string : line) {
                int num = Integer.parseInt(string);
                System.out.println(string + " " + (num % 2 == 0));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
