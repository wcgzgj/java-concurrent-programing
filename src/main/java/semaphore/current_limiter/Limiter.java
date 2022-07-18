package semaphore.current_limiter;

import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * @ClassName Limiter
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/18 13:29
 * @Version 1.0
 **/
public class Limiter<T,R> {
    final Semaphore semaphore;
    private int limitSize;
    private Vector<T> pool;


    public Limiter(int limitSize,T source) {
        this.limitSize = limitSize;
        this.semaphore = new Semaphore(limitSize);
        pool = new Vector<>();
        // 设定资源池内容
        for (int i = 0; i < limitSize; i++) {
            pool.add(source);
        }
    }

    /**
     * 限流器
     * @param func
     * @return
     * @throws InterruptedException
     */
    public R exec(Function<T,R>func) throws InterruptedException {
        T underUsedSource = null;
        semaphore.acquire();
        try {
            underUsedSource = pool.remove(0);
            return func.apply(underUsedSource);
        } finally {
            // 资源使用完毕，交还线程池
            pool.add(underUsedSource);
            semaphore.release();
        }
    }
}
