package monitor.blocking_queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName BlockingQueue
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/11 18:33
 * @Version 1.0
 **/
public class BlockingQueue<T> implements Queue<T>{
    ReentrantLock lock = new ReentrantLock();
    Condition notFull = lock.newCondition();
    Condition notEmpty = lock.newCondition();
    private Deque<T> queue = new LinkedList<T>();
    int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFull() {
        return capacity==queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean push(T val) throws InterruptedException {
        lock.lock();
        try {
            while (isFull()) {
                notFull.await();
            }
            queue.offerLast(val);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
        return true;
    }

    @Override
    public T pop() throws InterruptedException {
        T tmp;
        lock.lock();
        try {
            while (isEmpty()) {
                notEmpty.await();
            }
            tmp = queue.pollFirst();
            notFull.signalAll();
        } finally {
            lock.unlock();
        }
        return tmp;
    }

    @Override
    public T peek() {
        return queue.peekFirst();
    }
}
