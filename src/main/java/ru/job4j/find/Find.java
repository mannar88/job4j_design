package ru.job4j.find;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Map;

public class Find {

    private final static Map<String, SearchKey> TYPESERCH = Map.of("mask", new SearchMask(), "name", new SerchName(), "regex", new SearchRegex());

    private static ArgsName check(String[] arg) {
        ArgsName argsName = ArgsName.of(arg);
        if (arg.length < 4 || !new File(argsName.get("d")).isDirectory() || !TYPESERCH.containsKey(argsName.get("t"))) {
            throw new IllegalArgumentException(" Не верные ключи");
        }
        return argsName;
    }

    public static void main(String[] args) {
        ArgsName argsName = check(args);
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(argsName.get("o")))) {
            Search.search(Path.of(argsName.get("d")), TYPESERCH.get(argsName.get("t")).redicate(argsName.get("n"))).forEach(printWriter::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
