package lock_and_condition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ConcurrentQueue
 * @Description 使用管程，实现并发队列
 * 管程用显示锁实现，遵循 Mesa 模型
 * @Author faro_z
 * @Date 2022/5/11 2:03 下午
 * @Version 1.0
 **/
public class ConcurrentQueue {
    /**
     * 队列容器
     */
    Queue<Object> queue;
    /**
     * 队列最大容量
     */
    int capacity = 0;
    /**
     * 显示锁
     */
    final ReentrantLock lock = new ReentrantLock();
    /**
     * 条件变量
     */
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    public ConcurrentQueue(int capacity) {
        this.capacity = capacity;
        queue = new LinkedList<Object>();
    }

    /**
     * 入队操作
     * @param obj
     */
    public void inQue(Object obj) {
        lock.lock();
        try {
            // 管程的 MESA 模型
            while (isFull()) {
                notFull.await();
            }
            queue.add(obj);
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    /**
     * 出队操作
     * @return
     */
    public Object outQueue() {
        Object outQueRes = null;
        lock.lock();
        try {
            while (queue.size()<=0) {
                notEmpty.await();
            }
            outQueRes = queue.poll();
            notFull.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return outQueRes;
    }

    private boolean isFull() {
        return queue.size()>=capacity;
    }

    public int getSize() {
        return queue.size();
    }
}
