package thread_pool;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName SimpleThreadPoolCaller
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/27 18:14
 * @Version 1.0
 **/
public class SimpleThreadPoolCaller {
    public static void main(String[] args) throws InterruptedException {
        SimpleThreadPool executor = new SimpleThreadPool(5);
        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            executor.execute(()->{
                System.out.println("第："+ finalI +"个任务正在执行" );
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }
        latch.await();
        System.out.println("所有任务执行结束！！！！！");
    }
}
