package ext;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName InConcurrentListTest
 * @Description TODO
 * @Author faro_z
 * @Date 2022/5/14 4:20 下午
 * @Version 1.0
 **/
public class InConcurrentListTest {
    Queue<Object> queue = new LinkedList<>();
    @Test
    public void inOutQueueTest() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                int flag = i;
                Thread t = new Thread(() -> {
                    System.out.println(flag + ":add...");
                    queue.add(new Object());
                });
                t.start();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                int flag = i;
                Thread t = new Thread(() -> {
                    System.out.println(flag+":poll...");
                    queue.poll();
                });
                t.start();
            }
        });
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        System.out.println("queue size:"+queue.size());
    }
}
