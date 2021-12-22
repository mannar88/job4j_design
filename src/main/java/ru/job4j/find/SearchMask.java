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
            predicate = path -> regex(name, "*").matcher(path.toFile().getName()).find();
        }
        if (question && name.indexOf("?") == name.lastIndexOf("?")) {
            predicate = path -> path.toFile().getName().length() == name.length()
                    && regex(name, "?").matcher(path.toFile().getName()).find();
        }
        return predicate;
    }

    private Pattern regex(String name, String del) {
        int index = name.indexOf(del);
        String str = name.substring(0, index) + ".+" + name.substring(index + 1);
        Pattern pattern = Pattern.compile(str);
        return pattern;
    }
}
