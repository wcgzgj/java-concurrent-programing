package cdl_and_cb;

import cdl_and_cb.data.DeliveryOrder;
import cdl_and_cb.data.Diff;
import cdl_and_cb.data.OrderDB;
import cdl_and_cb.data.PreOrder;

import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName CdlExapmle
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/20 17:15
 * @Version 1.0
 **/
public class CdlExample {
    private OrderDB db = OrderDB.getInstance();
    private ExecutorService executor = Executors.newFixedThreadPool(2);

    public void checkAndSaveDiff() throws Exception {
        List<Long> list = db.getDiffUserIdList();
        System.out.println(list);
        CountDownLatch cdl = new CountDownLatch(2);
        for (Long id : list) {
            final PreOrder[] pods = new PreOrder[1];
            final DeliveryOrder[] dods = new DeliveryOrder[1];
            executor.execute(()->{
                pods[0] = db.getPOrderByUserId(id);
                cdl.countDown();
            });
            executor.execute(()-> {
                dods[0] = db.getDOrderByUserId(id);
                cdl.countDown();
            });
            // 等待线程池中的线程执行完毕
            // 因为线程池中的线程执行完毕后不会终止
            // 所以 join() 方法就无法使用了
            // 主线程想要获知子线程执行结束，必须使用 CountDownLatch
            cdl.await();

            Diff diff = db.diff(pods[0], dods[0]);
            db.saveDiff(diff);
        }

    }
}
