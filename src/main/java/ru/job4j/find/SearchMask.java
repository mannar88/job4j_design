package ru.job4j.find;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class SearchMask implements SearchKey {

    @Override
    public Predicate<Path> redicate(String name) {
        Predicate<Path> predicate = null;
        boolean star = name.contains("*");
        boolean question = name.contains("?");
        if (star == question) {
            throw new IllegalArgumentException("Не верная маска поиска");
        }
        if (star && name.indexOf("*") == name.lastIndexOf("*")) {
            int index = name.indexOf("*");
            String str = name.substring(0, index) + ".+" + name.substring(index + 1);
            predicate = path -> Pattern.matches(str, path.toFile().getName());
        }
        if (question && name.indexOf("?") == name.lastIndexOf("?")) {
            int index = name.indexOf("?");
            String str = name.substring(0, index) + ".+" + name.substring(index + 1);
            predicate = path -> path.toFile().getName().length() == name.length()
                    && Pattern.matches(str, path.toFile().getName());
        }
        return predicate;
    }
}
