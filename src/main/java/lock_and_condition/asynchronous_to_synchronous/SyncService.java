package lock_and_condition.asynchronous_to_synchronous;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @ClassName SyncService
 * @Description 使用 等待-通知 机制，实现异步转同步
 * @Author faro_z
 * @Date 2022/7/18 01:03
 * @Version 1.0
 **/
public class SyncService extends AsyncService {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition isDone = lock.newCondition();

    /**
     * 默认接口等待时间
     */
    private static final long DEFAULT_WAITING_TIME = 3000L;

    private static final Logger logger = Logger.getLogger("logger");
    static {
        logger.setLevel(Level.ALL);
    }

    @Override
    public boolean doService() throws Exception {
        super.doService();
        long startTime = System.currentTimeMillis();
        lock.lock();
        try {
            logger.info("异步未阻塞！");
            while (!isDone()) {
                isDone.await(DEFAULT_WAITING_TIME, TimeUnit.MILLISECONDS);
                long currTime = System.currentTimeMillis();
                if (isDone() || currTime-startTime>DEFAULT_WAITING_TIME) {
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        if (!isDone()) {
            throw new Exception("调用超时！");
        }
        return true;
    }

    /**
     * 接口执行结束后的回调
     */
    @Override
    void invokeCallBack(Response resp) {
        lock.lock();
        try {
            this.resp = resp;
            if (isDone!=null) {
                isDone.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    private boolean isDone() {
        return this.resp!=null;
    }
}
