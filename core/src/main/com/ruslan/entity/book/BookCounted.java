package com.ruslan.entity.book;

import java.util.concurrent.atomic.AtomicInteger;

public class BookCounted {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    public static int generateNewId() {
        return COUNTER.getAndIncrement();
    }


}
