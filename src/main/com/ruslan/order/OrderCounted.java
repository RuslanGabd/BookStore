package com.ruslan.order;

import java.util.concurrent.atomic.AtomicInteger;

public class OrderCounted {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    public static int generateNewId() {
        return COUNTER.getAndIncrement();
    }

}
