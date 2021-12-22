package ru.job4j.find;

import java.nio.file.Path;
import java.util.function.Predicate;

public class SerchName implements SearchKey {

    @Override
    public Predicate<Path> redicate(String name) {
        return path -> path.toFile().getName().equals(name);
    }
}
