package com.ruslan;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDate {
    final LocalDate MIN_DATE  =   LocalDate.of(1970, 1, 1);
    final LocalDate MAX_DATE  =   LocalDate.of(2023, 06, 30);


    public LocalDate generateDateForTest() {
        long minDay = MIN_DATE.toEpochDay();
        long maxDay = MAX_DATE.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return randomDate;
    }
    public LocalDate generateDateExecutionForOrder() {
        long minDay = LocalDate.of(2023, 01, 01).toEpochDay();
        long maxDay = LocalDate.of(2023, 04, 01).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return randomDate;
    }
 }
