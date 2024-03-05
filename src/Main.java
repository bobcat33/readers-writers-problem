public class Main {
    public static void main(String[] args) throws InterruptedException {

        new Reader(1).start();
        new Reader(2).start();
        new Writer(1).start();
        new Reader(3).start();
    }
}
