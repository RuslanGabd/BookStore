package ex4;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new TimePrinter(5));
        thread.start();
    }
}
