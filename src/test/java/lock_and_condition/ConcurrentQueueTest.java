package lock_and_condition;


import lock_and_condition.concurrent_queue.ConcurrentQueue;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class ConcurrentQueueTest {
    private final ConcurrentQueue concurrentQueue = new ConcurrentQueue(10);
    private final Queue<Object> queue = new LinkedList<>();
    private final int iterNum = 100;

    @Test
    public void concurrentTest() throws InterruptedException {
        Thread inQueThread = new Thread(() -> {
            for (int i = 0; i < iterNum; i++) {
                try {
                    Thread t = new Thread(() -> {
                        concurrentQueue.inQue(new Object());
                    });
                    t.start();
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread outQueThread = new Thread(() -> {
            for (int i = 0; i < iterNum; i++) {
                try {
                    Thread t = new Thread(() -> {
                        concurrentQueue.outQueue();
                    });
                    t.start();
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        inQueThread.start();
        outQueThread.start();
        inQueThread.join();
        outQueThread.join();
        //Thread.sleep(1000);
        System.out.println("after thread res:"+concurrentQueue.getSize());
    }

    @Test
    public void inConcurrentTest() throws InterruptedException {
        Thread inQueThread = new Thread(() -> {
            for (int i = 0; i < iterNum; i++) {
                try {
                    Thread t = new Thread(() -> {
                        queue.add(new Object());
                    });
                    t.start();
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread outQueThread = new Thread(() -> {
            for (int i = 0; i < iterNum; i++) {
                try {
                    Thread t = new Thread(() -> {
                        if (queue.size()>0) {
                            queue.poll();
                        }
                    });
                    t.start();
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        inQueThread.start();
        outQueThread.start();
        inQueThread.join();
        outQueThread.join();
        System.out.println("after thread res:"+ queue.size());
    }
}