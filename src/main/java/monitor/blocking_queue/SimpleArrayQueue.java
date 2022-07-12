package monitor.blocking_queue;

/**
 * @ClassName BlockingQueue
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/11 17:30
 * @Version 1.0
 **/
public class SimpleArrayQueue<T> implements Queue<T>{
    private int capacity;
    private T[] container;
    private int head;
    private int tail;
    private int size = 0;

    public SimpleArrayQueue(int capacity) throws Exception {
        if (capacity<0) {
            throw new Exception("队列大小设置错误！");
        }
        this.capacity = capacity;
        head = 0;
        tail = -1;
        container = (T[]) new Object[capacity];
    }


    @Override
    public boolean push(T val) {
        if (isFull()) {
            return false;
        }
        tail = (tail+1)%capacity;
        container[tail] = val;
        ++size;
        return true;
    }

    @Override
    public T pop() {
        System.out.println("queue is empty");
        if (isEmpty()) {
            return null;
        }
        T retVal = container[head];
        head = (head+1)%capacity;
        --size;
        return retVal;
    }

    @Override
    public T peek() {
        return container[head];
    }

    public int getSize() {
        return size;
    }

    public boolean isFull() {
        if (tail==-1) {
            return false;
        }
        if (tail<head) {
            return head-tail==1;
        }
        return tail-head+1==capacity;
    }

    public boolean isEmpty() {
        return head==tail || tail==-1;
    }
}
