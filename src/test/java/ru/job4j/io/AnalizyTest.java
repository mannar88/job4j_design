package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalizyTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void thenUnavailable() throws IOException {
        File source = temporaryFolder.newFile("source.txt");
        File target = temporaryFolder.newFile("target.txt");
        try (PrintWriter printWriter = new PrintWriter(source)) {
            printWriter.println("200 10:56:01");
            printWriter.println("500 10:57:01");
            printWriter.println("400 10:58:01");
            printWriter.println("200 10:59:01");
            printWriter.println("500 11:01:02");
            printWriter.println("200 11:02:02");
        }
        new Analizy().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(target))) {
            bufferedReader.lines().forEach(stringBuilder::append);
        }
        assertThat(stringBuilder.toString(), is(
                "10:57:01;10:59:01;"
+ "11:01:02;11:02:02;"));
    }


}