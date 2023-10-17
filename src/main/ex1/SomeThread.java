package ex1;

import static java.lang.Thread.sleep;

public class SomeThread implements Runnable {
    @Override
    public void run() {
        System.out.println("Must be status 'runnable': " + Thread.currentThread().getState());
        try {
            sleep(1000);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}