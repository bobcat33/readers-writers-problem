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
        wait(mutex);
        incrementReaders();
        if (readers == 1) {
            wait(rw_mutex);
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
            signal(rw_mutex);
        }
        signal(mutex);
    }

}
