package atomic.cas;

import atomic.atomic_examples.AtomicAdder;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName CAS
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/26 15:36
 * @Version 1.0
 **/
public class CAS {
    private volatile int count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(100);
        CAS cas = new CAS();
        for (int i = 0; i < 100; i++) {
            executor.execute(()->{
                cas.addOneK();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(cas.getCount());
        executor.shutdown();
    }

    public void addOneK() {
        int index = 0;
        while (index++<1000) {
            addOne();
        }
    }

    public void addOne() {
        int expect = 0;
        do {
            expect = this.count;
        } while (!cas(expect,expect+1));
    }

    /**\
     * CAS 的 java 实现
     * 虽然我们称 CAS 是无锁的，但是其是由 CPU 保证的操作原子性
     * 如果我们想自己在 java 中复现，只能自己加锁保证原子性（但这不代表 CAS 不是无锁实现）
     * @param expect
     * @param newVal
     * @return
     */
    public synchronized boolean cas(int expect,int newVal) {
        // this.count 相当于内存中的值了
        int currVal = this.count;
        // 如果原内存中的值与期望值相符，修改内存中的值
        if (currVal==expect) {
            count = newVal;
            return true;
        }
        // 返回写入前的值
        // 如果更新失败，可以借助新的内存值在此执行修改
        return false;
    }

    public int getCount() {
        return count;
    }
}
