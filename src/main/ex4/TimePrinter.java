package ex4;

import java.util.concurrent.TimeUnit;

public class TimePrinter implements Runnable {
    private final int interval;

    public TimePrinter(int interval) {
        this.interval = interval;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(java.time.LocalTime.now());
                TimeUnit.SECONDS.sleep(interval);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}