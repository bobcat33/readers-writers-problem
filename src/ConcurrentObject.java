public class ConcurrentObject extends Thread {

    protected static final Lock rw_mutex = new Lock(), mutex = new Lock();

    protected static void wait(Lock mutex) {
        mutex.lock();
    }

    protected static void signal(Lock mutex) {
        mutex.unlock();
    }

}
