package ru.job4j.find;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class SearchRegex implements SearchKey {
    @Override
    public Predicate<Path> redicate(String name) {
        Pattern pattern = Pattern.compile(name);
        return path -> pattern.matcher(path.toFile().getName()).find();
    }
}
