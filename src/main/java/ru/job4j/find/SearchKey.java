package ru.job4j.find;

import java.nio.file.Path;
import java.util.function.Predicate;

public interface SearchKey {
    Predicate<Path> redicate(String name);
}
