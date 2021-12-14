package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<String>> pathes = new HashMap<>();

    public void printDuplicate() {
        pathes.values().stream()
                .filter(l -> l.size() > 1)
                .flatMap(List::stream)
                .forEach(System.out::println);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().getName(), file.toFile().length());
        pathes.putIfAbsent(fileProperty, new ArrayList<>());
        pathes.get(fileProperty).add(file.toFile().getAbsolutePath());
        return super.visitFile(file, attrs);
    }
}