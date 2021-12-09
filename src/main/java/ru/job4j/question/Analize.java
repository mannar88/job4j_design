package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int del = 0;
        int changed = 0;
        Map<Integer, String> map = current.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        for (User user : previous) {
            String name = map.remove(user.getId());
            if (name != null && !name.equals(user.getName())) {
                changed++;
            }
            if (name == null) {
                del++;
            }
        }
        return new Info(map.size(), changed, del);
    }
}