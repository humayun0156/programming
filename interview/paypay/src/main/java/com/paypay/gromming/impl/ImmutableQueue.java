package com.paypay.gromming.impl;


import com.paypay.gromming.api.Queue;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class ImmutableQueue<T> implements Queue<T> {

    private final List<T> items = new ArrayList<>();

    @Override
    public Queue<T> enQueue(T o) {
        items.add(o);
        return new UnmodifiableQueue<>(items);
    }

    @Override
    public Queue<T> deQueue() {
        if (!items.isEmpty()) {
            items.remove(0);
        }
        return new UnmodifiableQueue<>(items);
    }

    @Override
    public T head() {
        return items.isEmpty() ? null : items.get(0);
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    private static class UnmodifiableQueue<T> implements Queue<T> {
        final List<T> items;

        UnmodifiableQueue(List<T> items) {
            this.items = items;
        }

        @Override
        public Queue<T> enQueue(T t) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Queue<T> deQueue() {
            throw new UnsupportedOperationException();
        }

        @Override
        public T head() {
            return items.isEmpty() ? null : items.get(0);
        }

        @Override
        public boolean isEmpty() {
            return items.isEmpty();
        }
    }
}
