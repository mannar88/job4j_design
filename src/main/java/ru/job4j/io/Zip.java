package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(in.readAllBytes());
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        List<File> files = paths.stream()
                .map(p -> p.toFile())
                .collect(Collectors.toList());
        packFiles(files, new File(argsName.get("o")));
    }
}