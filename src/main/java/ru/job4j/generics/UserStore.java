package ru.job4j.generics;

public class UserStore implements Stor<User> {

    private final Stor<User> store = new MemStore<>();

    @Override
    public void add(User model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        return store.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return store.delete(id);
    }

    @Override
    public User findById(String id) {
        return store.findById(id);
    }
}
