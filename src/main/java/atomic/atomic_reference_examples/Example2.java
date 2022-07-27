package atomic.atomic_reference_examples;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName Example2
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/27 14:59
 * @Version 1.0
 **/
public class Example2 {

    /**
     * private int up;
     * private int down;
     */
    static class UDRange {
        private int up;
        private int down;

        public UDRange(int up, int down) {
            this.up = up;
            this.down = down;
        }

        public int getUp() {
            return up;
        }

        public void setUp(int up) {
            this.up = up;
        }

        public int getDown() {
            return down;
        }

        public void setDown(int down) {
            this.down = down;
        }
    }

    /**
     * 这里之所以要把上下限 wrap 成一个对象
     * 是因为如果单纯的只用两个 AtomicInteger 做上下限的话，会出现竞态，导致可能出现的上下限不合法
     * 要解决这个问题，就需要加锁，自然性能就会下降
     * 而 warp 后，就可以借助 cas ，更新范围的整体，效率优于加锁
     */
    final AtomicReference<UDRange> range = new AtomicReference<UDRange>();

    /**
     * 设置上限，借助 cas 进行更新，效率优于悲观锁
     * 但是有很小的概率出现 ABA 问题
     * @param up
     * @throws Exception
     */
    public void updateUp(int up) throws Exception {
        UDRange oldRange = null;
        UDRange newRange = null;
        do {
            oldRange = range.get();
            if (up<=oldRange.down) {
                throw new Exception("上限应高于下限"+oldRange.down);
            }
            newRange = new UDRange(up, oldRange.down);
        } while (!range.compareAndSet(oldRange,newRange));
    }

    /**
     * 设置下限，具体实现同设置上限
     */
    public void updateDown() {
        // ....
    }

}
