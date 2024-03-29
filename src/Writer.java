public class Writer extends ConcurrentObject {
    private static int maxWriterId = 0;

    private final int writerNo;

    public Writer() {
        writerNo = maxWriterId++;
    }

    public Writer(int writerNo) {
        this.writerNo = writerNo;
    }

    @Override
    public void run() {
        wait(rw_mutex);

        System.out.println("Writing with writer " + writerNo);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        /* Write stuff */
        System.out.println("Finished writing with writer " + writerNo);

        signal(rw_mutex);
    }

}
