public class Main {
    public static void main(String[] args) throws InterruptedException {

        new Reader(1).start();
        Thread.sleep(100);
        new Reader(2).start();
        Thread.sleep(100);
        new Writer(1).start();
        Thread.sleep(3100);
        new Reader(3).start();
    }
}
