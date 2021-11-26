package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        pourOver(in, out);
        T res = out.pop();
        pourOver(out, in);
        return res;
    }

    public void push(T value) {
        in.push(value);
    }

    private void pourOver(SimpleStack<T> left, SimpleStack<T> right) {
        while (left.getSize() > 0) {
            right.push(left.pop());
        }
    }
}