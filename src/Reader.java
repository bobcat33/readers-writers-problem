public class Reader extends ConcurrentObject {
    private static int maxReaderNo = 0;

    private static int readers = 0;

    private final int readerNo;

    public Reader() {
        readerNo = maxReaderNo++;
    }

    public Reader(int readerNo) {
        this.readerNo = readerNo;
    }

    @Override
    public void run() {
        System.out.println("Reader " + readerNo + " started.");
        wait(mutex);
        System.out.println("Reader " + readerNo + " in lock for mutex");
        readers++;
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
        readers--;
        if (readers == 0) {
            System.out.println("Reader " + readerNo + " releasing rw_mutex...");
            signal(rw_mutex);
            System.out.println("Reader " + readerNo + " released rw_mutex");
        }
        signal(mutex);
    }

}
