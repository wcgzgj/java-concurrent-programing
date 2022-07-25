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
