package ex1;

import static java.lang.Thread.sleep;

public class StatesStart {
    public static void main(String[] args) {
        try {
            Thread thread = new Thread(new SomeThread());
            Thread thread2 = new Thread(new SlowThread());
            System.out.println("Must be status 'new' : " + thread.getState());
            thread.start();
            thread2.start();
            sleep(500);
            System.out.println("Must be timed_waiting : " + thread.getState());

            System.out.println("Must be status 'waiting' : " + thread2.getState());
            Object a = SlowThread.getA();
            synchronized (a) {
                a.notify();
            }

            System.out.println("Must be status 'blocked' : " + thread2.getState());
            sleep(2000);
            System.out.println("Must be status 'terminated' thread 2 : " + thread2.getState());
            System.out.println("Must be status 'terminated' : " + thread.getState());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}