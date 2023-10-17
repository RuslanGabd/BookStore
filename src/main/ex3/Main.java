package ex3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    private static final int BUFFER_SIZE = 5;
    private static final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(BUFFER_SIZE);

    public static void main(String[] args) {
        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue)).start();
    }
}