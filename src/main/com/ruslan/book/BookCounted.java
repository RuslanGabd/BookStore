package com.ruslan.book;

import java.util.concurrent.atomic.AtomicInteger;

public class BookCounted {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    private final int id;

    public BookCounted() {
        id = COUNTER.getAndIncrement();
    }

    public int getId() {
        return id;
    }

}
