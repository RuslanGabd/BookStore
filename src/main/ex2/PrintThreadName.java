package ex2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintThreadName {

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    Runnable printThreadName = () -> {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName());
                condition.signal();
                condition.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    };
}
