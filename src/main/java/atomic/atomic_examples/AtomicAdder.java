package atomic.atomic_examples;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName AtomicAdder
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/26 14:59
 * @Version 1.0
 **/
public class AtomicAdder {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(100);
        AtomicAdder adder = new AtomicAdder();
        for (int i = 0; i < 100; i++) {
            executor.execute(()->{
                adder.addOneK();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(adder.getCounter().get());
    }

    private AtomicInteger counter = new AtomicInteger(0);

    public void addOneK() {
        for (int i = 0; i < 1000; i++) {
            // counter++
            counter.getAndIncrement();

            // ++counter
            //counter.incrementAndGet();
        }
    }

    public AtomicInteger getCounter() {
        return counter;
    }
}
