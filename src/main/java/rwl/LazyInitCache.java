package rwl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName LazyInitCache
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/18 15:29
 * @Version 1.0
 **/
public class LazyInitCache<K,V> {
    private final Map<K,V> cache = new HashMap<>();
    final ReadWriteLock rwl = new ReentrantReadWriteLock();
    final Lock r = rwl.readLock();
    final Lock w = rwl.writeLock();

    /**
     * 按需加载的简单并发缓存实现
     * @param key
     * @return
     */
    public V get(K key) {
        V val = null;
        r.lock();
        try {
            val = cache.get(key);
        } finally {
            r.unlock();
        }
        if (val==null) {
            w.lock();
            try {
                val = cache.get(key);
                // double-check 避免重复修改缓存
                if (val==null) {
                    //模拟从数据库读取数据
                    val = readFromDB();
                    // 放入缓存
                    cache.put(key,val);
                }
            } finally {
                w.unlock();
            }
        }
        return val;
    }

    public void put(K key,V val) {
        w.lock();
        try {
            cache.put(key,val);
        } finally {
            w.unlock();
        }
    }

    private V readFromDB() {
        // 模拟 IO 和缓存之间的延迟
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (V) new Object();
    }
}
