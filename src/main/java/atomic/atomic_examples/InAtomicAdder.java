package atomic.atomic_examples;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName InAtomicAdder
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/26 15:06
 * @Version 1.0
 **/
public class InAtomicAdder {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(100);
        InAtomicAdder adder = new InAtomicAdder();
        for (int i = 0; i < 100; i++) {
            executor.execute(()->{
                adder.addOneK();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(adder.getCounter());
        executor.shutdown();
    }

    private volatile int counter = 0;

    //public synchronized void addOneK() {
    //    for (int i = 0; i < 100000; i++) {
    //        counter++;
    //    }
    //}

    public void addOneK() {
        for (int i = 0; i < 100000; i++) {
            counter++;
        }
    }

    public int getCounter() {
        return counter;
    }
}
