package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Set<FileProperty> pathes = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println(file.toAbsolutePath());
        if (!pathes.add(new FileProperty(file.toFile().getName(), file.toFile().length()))) {
            Files.delete(file);
            System.out.println("Дубликат: " + file.getFileName() + " удален!");
        }

        return super.visitFile(file, attrs);
    }
}