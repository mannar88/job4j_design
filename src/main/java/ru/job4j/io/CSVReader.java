package ru.job4j.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {

        List<List<String>> spliter = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(argsName.get("path"))).useDelimiter(";")) {
            while (scanner.hasNext()) {
                spliter.add(Arrays.asList(scanner.nextLine().split(";")));
            }
            List<Integer> indexes = search(spliter.get(0), argsName.get("filter"));
            String table = text(assembly(spliter, indexes), argsName.get("delimiter"));
            if ("stdout".equals(argsName.get("out"))) {
                System.out.println(table);
            } else {
                printFile(table, argsName.get("out"));
            }
        }
    }

    private static List<Integer> search(List<String> spliter, String filter) {
        List<Integer> rsl = new ArrayList<>();
        List<String> chek = Arrays.asList(filter.split(","));
        for (int i = 0; i < spliter.size(); i++) {
            if (chek.contains(spliter.get(i))) {
                rsl.add(i);
            }
        }
        return rsl;
    }

    private static List<List<String>> assembly(List<List<String>> spleter, List<Integer> search) {
        List<List<String>> rsl = new ArrayList<>();
        for (int i = 0; i < spleter.size(); i++) {
            rsl.add(new ArrayList<>());
            for (int index : search) {
                rsl.get(i).add(spleter.get(i).get(index));
            }
        }
        return rsl;
    }

    private static String text(List<List<String>> assembly, String del) {
        StringJoiner stringJoinerTwo = new StringJoiner(System.lineSeparator());
        for (List<String> list : assembly) {
            StringJoiner stringJoinerOne = new StringJoiner(del);
            list.forEach(stringJoinerOne::add);
            stringJoinerTwo.add(stringJoinerOne.toString());
        }
        return stringJoinerTwo.toString();
    }

    private static void printFile(String text, String out) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(out))) {
            writer.println(text);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    private static ArgsName check(String[] arg) {
        ArgsName argsName = ArgsName.of(arg);
        if (arg.length != 4 && !new File(argsName.get("path")).exists() && !";".equals(argsName.get("delimiter"))) {
            throw new IllegalArgumentException(" не коорректны аргументы");
        }
        return argsName;
    }

    public static void main(String[] args) {
        try {
            handle(check(args));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}