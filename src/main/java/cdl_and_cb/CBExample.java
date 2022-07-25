package cdl_and_cb;

import cdl_and_cb.data.DeliveryOrder;
import cdl_and_cb.data.Diff;
import cdl_and_cb.data.OrderDB;
import cdl_and_cb.data.PreOrder;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName CBExample
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/25 15:18
 * @Version 1.0
 **/
public class CBExample {
    private final Vector<PreOrder> preOrders = new Vector<>();
    private final Vector<DeliveryOrder> deliveryOrders = new Vector<>();
    private final OrderDB db = OrderDB.getInstance();
    private final ExecutorService executor = Executors.newFixedThreadPool(1);
    private final CyclicBarrier cyclicBarrier = new CyclicBarrier(2,()-> {
        executor.execute(()->{
            check();
        });
    });

    /**
     * 执行差 diff 和 save 的操作
     */
    private void check() {
        // 下面这两个操存在竞态（虽然 vector.remove是线程阿安全的，但是两个操作都执行不一定是原子的）
        // 比如两个线程 T1，T2，
        // T1 执行 pOrders.remove(0)，获得 p1，线程切换;
        // T2 执行 pOrders.remove(0)，获得 p2；执行 dOrders.remove(0)，获得 d1，线程切换；
        // T1 执行 dOrders.remove(0)，获得 d2；
        // 最后结果就是 T1 获得 p1,d2，T2 获得 p2,d1，不符合要求
        // 所以 CyclicBarrier 的回调函数中，我们才设置 大小为1的线程池
        // 目的就是让 check() 相对于主线程并行，但是 check() 之间是串行的，从而达到线程安全的效果
        PreOrder preOrder = preOrders.remove(0);
        DeliveryOrder deliveryOrder = deliveryOrders.remove(0);
        Diff diff = null;
        try {
            diff = db.diff(preOrder, deliveryOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.saveDiff(diff);
    }

    public void checkAll() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Long> diffUserIdList = db.getDiffUserIdList();
        for (Long userId : diffUserIdList) {
            executorService.execute(()->{
                preOrders.add(db.getPOrderByUserId(userId));
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            executorService.execute(()->{
                deliveryOrders.add(db.getDOrderByUserId(userId));
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
