public class Reader extends ConcurrentObject {
    private final int readerNo;

    private static volatile int readers = 0;

    public Reader(int readerNo) {
        this.readerNo = readerNo;
    }

    private synchronized void incrementReaders() { readers++; }
    private synchronized void decrementReaders() { readers--; }

    @Override
    public void run() {
        System.out.println("Reader " + readerNo + " started.");
        wait(mutex);
        System.out.println("Reader " + readerNo + " in lock for mutex");
        incrementReaders();
        if (readers == 1) {
            System.out.println("Reader " + readerNo + " awaiting rw_mutex...");
            wait(rw_mutex);
            System.out.println("Reader " + readerNo + " received rw_mutex...");
        }
        signal(mutex);

        System.out.println("Reading with reader " + readerNo);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        /* read stuff */
        System.out.println("Finished reading with reader " + readerNo);

        wait(mutex);
        decrementReaders();
        if (readers == 0) {
            System.out.println("Reader " + readerNo + " releasing rw_mutex...");
            signal(rw_mutex);
            System.out.println("Reader " + readerNo + " released rw_mutex");
        }
        signal(mutex);
    }

}
