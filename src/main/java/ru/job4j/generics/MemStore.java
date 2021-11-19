package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Stor<T> {
    private final Map<String, T> mem = new HashMap<>();

    @Override
    public void add(T model) {
        mem.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        return mem.replace(id, findById(id), model);
    }

    @Override
    public boolean delete(String id) {
        return mem.remove(id, findById(id));
    }

    @Override
    public T findById(String id) {
        if (id == null) {
            throw new NullPointerException();
        }
        return mem.get(id);
    }
}
