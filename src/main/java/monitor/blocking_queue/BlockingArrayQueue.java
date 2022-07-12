package monitor.blocking_queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName BlockingArrayQueue
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/11 17:40
 * @Version 1.0
 **/
public class BlockingArrayQueue {
    private Queue queue;
    private int capacity;
    ReentrantLock lock = new ReentrantLock();
    Condition isFull = lock.newCondition();
    Condition isEmpty = lock.newCondition();

    public BlockingArrayQueue(int capacity) throws Exception {

    }
}
