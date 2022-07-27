package atomic.atomic_reference_examples;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName Example3
 * @Description 使用 AtomicStampedReference 解决 ABA 问题
 * @Author faro_z
 * @Date 2022/7/27 15:26
 * @Version 1.0
 **/
public class ABA {
    private static final ExecutorService executor = Executors.newFixedThreadPool(2);
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        ABA aba = new ABA();
        AtomicBoolean success = new AtomicBoolean(true);
        executor.execute(()->{
            success.set(aba.doCas());
            latch.countDown();
        });
        executor.execute(()->{
            aba.changeAndChangeBack();
            latch.countDown();
        });
        latch.await();
        System.out.println(success); // false
        executor.shutdown();
    }

    private final AtomicStampedReference<Integer> reference =new AtomicStampedReference<>(100,0);

    /**
     * 100 -> 101 -> 100
     * @return
     */
    public void changeAndChangeBack() {
        reference.compareAndSet(100,101,
                reference.getStamp(),reference.getStamp()+1);
        reference.compareAndSet(101,101,
                reference.getStamp(),reference.getStamp()+1);
    }

    /**
     * 100 -> 101
     * @return
     */
    public boolean doCas() {
        Integer oldVal = reference.getReference();
        int oldStamp = reference.getStamp();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return reference.compareAndSet(oldVal,oldVal+1,oldStamp,oldStamp+1);
    }

}
