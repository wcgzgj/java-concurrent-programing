package thread_pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName SimpleThreadPool
 * @Description 实现一个简易的线程池
 * @Author faro_z
 * @Date 2022/7/27 17:58
 * @Version 1.0
 **/
public class SimpleThreadPool {
    //new LinkedBlockingQueue<>

    private final int poolSize;
    private final List<WorkingThread> pool;
    private final BlockingQueue<Runnable> workingQueue;

    /**
     * 线程池初始化
     * @param poolSize
     */
    public SimpleThreadPool(int poolSize,BlockingQueue<Runnable> workingQueue) {
        this.poolSize = poolSize;
        this.workingQueue = workingQueue;
        pool = new ArrayList<>();
        for (int i = 0; i < poolSize; i++) {
            WorkingThread t = new WorkingThread();
            t.start();
            pool.add(t);
        }
    }

    public void execute(Runnable task) {
        try {
            workingQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class WorkingThread extends Thread{
        @Override
        public void run() {
            while (true) {
                try {
                    Runnable task = workingQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
