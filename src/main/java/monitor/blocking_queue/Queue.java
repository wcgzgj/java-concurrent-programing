package monitor.blocking_queue;

public interface Queue<T> {
    boolean push(T val) throws InterruptedException;
    T pop() throws InterruptedException;
    T peek();
}
