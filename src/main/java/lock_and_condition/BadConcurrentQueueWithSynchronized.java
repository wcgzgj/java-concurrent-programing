package lock_and_condition;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName BadConcurrentQueueWithSynchronized
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/15 18:02
 * @Version 1.0
 **/
public class BadConcurrentQueueWithSynchronized {
    private int capacity;
    private Queue queue;

    public BadConcurrentQueueWithSynchronized(int capacity) {
        this.capacity = capacity;
        queue = new LinkedList();
    }

    public synchronized void inQue(Object obj) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        queue.offer(obj);
        // 这里需要唤醒那些调用 outQue 的线程
        // 但是因为只有一个条件变量，所以 inQue 和 outQue 的条件变量等待队列是共用的
        // 这就会导致唤醒一些我们不想唤醒的 inQue 线程，造成额外 CPU 开销
        notifyAll();
    }

    public synchronized Object outQue() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        Object poll = queue.poll();
        // 不太好的原因和 inQue 那里同理
        notifyAll();
        return poll;
    }

    private boolean isFull() {
        return queue.size()==capacity;
    }

    private boolean isEmpty() {
        return queue.size()==0;
    }
}
