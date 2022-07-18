package rwl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName SimpleCache
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/18 15:20
 * @Version 1.0
 **/
public class SimpleCache<K,V> {
    private final Map<K,V> cache = new HashMap<>();
    final ReadWriteLock rwl = new ReentrantReadWriteLock();
    final Lock r = rwl.readLock();
    final Lock w = rwl.writeLock();

    public V get(K key) {
        r.lock();
        try {
            return cache.get(key);
        } finally {
            r.unlock();
        }
    }

    public void put(K key,V val) {
        w.lock();
        try {
            cache.put(key,val);
        } finally {
            w.unlock();
        }
    }
}
