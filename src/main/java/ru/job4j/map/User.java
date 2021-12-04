package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Objects;

public class User {
    private final String name;
    private final int children;
    private final Calendar birthday;

    public User(String name, int children) {
        this.name = name;
        this.children = children;
        birthday = new GregorianCalendar(2021, 11, 05);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public String toString() {
        return "User{" 
+               "name='" + name + '\'' 
+                ", children=" + children 
+                ", birthday=" + birthday.getTime() 
        +                '}';
    }

    public static void main(String[] args) {
        User one = new User("User", 1);
        User two = new User("User", 1);
        Map<User, Object> map = Map.of(one, new Object(), two, new Object());
        map.forEach((user, object) -> System.out.println(user + " " + object));
    }
}
