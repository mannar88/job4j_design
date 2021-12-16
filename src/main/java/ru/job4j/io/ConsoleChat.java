package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private final List<String> answers;
    private boolean chat = true;
    private final List<String> record = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
        answers = readPhrases();
    }

    public static void main(String[] rgs) {
        ConsoleChat cc = new ConsoleChat("./data/log_chat.txt", "./data/answers.txt");
        cc.run();
    }

    public void run() {
        String in = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            in = reader.readLine();
            in = in.trim();
            record.add("Пользователь: " + in);
            if (in.equalsIgnoreCase(OUT)) {
                saveLog();
            } else {
                work(in);
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    private void work(String in) {
        if (in.equalsIgnoreCase(STOP)) {
            chat = false;
        }
        if (CONTINUE.equalsIgnoreCase(in)) {
            chat = true;
        }
        if (chat) {
            int max = answers.size();
            int rnd = (int) (Math.random() * max);
            System.out.println(answers.get(rnd));
            record.add("Робот: " + answers.get(rnd));
        }
        run();

    }

    private List<String> readPhrases() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            answers = reader.lines().collect(Collectors.toList());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return answers;
    }

    private void saveLog() {
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(path))) {
            record.forEach(printWriter::println);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}