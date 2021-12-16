package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            for (Path file : sources) {
                zip.putNextEntry(new ZipEntry(file.toFile().getPath()));
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file.toFile()))) {
                    zip.write(in.readAllBytes());
                }
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static void main(String[] args)  {
        ArgsName argsName = ArgsName.of(args);
        Path start = Paths.get(argsName.get("d"));
        List<Path> paths = null;
        try {
            if (!start.toFile().isDirectory()) {
                throw  new IOException("такой директории нет");
            }

            paths = Search.search(start, path -> !path.toFile().getName().endsWith(argsName.get("e")));

        } catch (IOException e) {
        e.printStackTrace();
        }
        packFiles(paths, new File(argsName.get("o")));
    }
}