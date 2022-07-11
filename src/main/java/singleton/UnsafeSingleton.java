package singleton;

/**
 * @ClassName UnsafeSingleton
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/6 16:17
 * @Version 1.0
 **/
public class UnsafeSingleton {
    // 加上 volatile ，禁止重排，可以避免并发有序性导致的问题
    //private static volatile UnsafeSingleton instance;
    private static UnsafeSingleton instance;
    public static UnsafeSingleton getInstance() {
        if (instance==null) {
            synchronized (UnsafeSingleton.class) {
                if (instance==null) {
                    instance = new UnsafeSingleton();
                }
            }
        }
        return instance;
    }
}
