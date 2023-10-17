package ex2;

public class Main {
    public static void main(String[] args) {

        PrintThreadName printThreadName = new PrintThreadName();

        Thread t1 = new Thread(printThreadName.printThreadName, "Thread 1");
        Thread t2 = new Thread(printThreadName.printThreadName, "Thread 2");

        t1.start();
        t2.start();
    }
}
