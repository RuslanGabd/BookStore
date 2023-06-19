package com.ruslan.order;

import java.util.concurrent.atomic.AtomicInteger;

public class OrderCounted {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    private final int id;

    public OrderCounted() {
        id = COUNTER.getAndIncrement();
    }

    public int getId() {
        return id;
    }
}
