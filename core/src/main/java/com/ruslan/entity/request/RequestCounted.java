package com.ruslan.entity.request;

import java.util.concurrent.atomic.AtomicInteger;

public class RequestCounted {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    public static int generateNewId() {
        return COUNTER.getAndIncrement();
    }
}
