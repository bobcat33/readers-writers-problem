import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentObject extends Thread {

    protected static final ReentrantLock rw_mutex = new ReentrantLock(true), mutex = new ReentrantLock(true);

    protected static void wait(ReentrantLock mutex) {
        mutex.lock();
    }

    protected static void signal(ReentrantLock mutex) {
        mutex.unlock();
    }

}
