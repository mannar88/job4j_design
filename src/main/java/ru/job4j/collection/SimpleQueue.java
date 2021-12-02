package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        shift(in, out);
        return out.pop();
    }

    public void push(T value) {
        shift(out, in);
        in.push(value);
    }

    private void shift(SimpleStack<T> donor, SimpleStack<T> recipient) {
        while (donor.getHasNext()) {
            recipient.push(donor.pop());
        }
    }

}