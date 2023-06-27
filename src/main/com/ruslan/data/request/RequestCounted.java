package com.ruslan.data.request;

import java.util.concurrent.atomic.AtomicInteger;

public class RequestCounted {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    private final int id;

    public RequestCounted() {
        id = COUNTER.getAndIncrement();
    }

    public int getId() {
        return id;
    }
}
