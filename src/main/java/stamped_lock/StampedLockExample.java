package stamped_lock;

import java.util.concurrent.locks.StampedLock;

/**
 * @ClassName StampedLockExample
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/20 15:34
 * @Version 1.0
 **/
public class StampedLockExample {
    private int x;
    private int y;
    StampedLock sl = new StampedLock();

    public int calculateSum() {
        long stamp = sl.tryOptimisticRead();
        int currX = this.x;
        int currY = this.y;
        // 如果检查发现在读的过程中，发生了写的行为
        // 直接将乐观读锁升级为悲观读锁，
        // 从而保证读的过程中，==不会读取修改的中间值==
        if (!sl.validate(stamp)) {
            stamp = sl.readLock();
            try {
                currX = this.x;
                currY = this.y;
            } finally {
                sl.unlockRead(stamp);
            }
        }
        return currX+currY;
    }

}
