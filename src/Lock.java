public class Lock {

    private volatile int mutex = 1;

    public void lock() {
        while (mutex <= 0) Thread.onSpinWait();
        getLock();
    }

    private synchronized void getLock() {
        mutex--;
    }

    public synchronized void unlock() {
        mutex++;
    }

}
