package util;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName SleepUtil
 * @Description TODO
 * @Author faro_z
 * @Date 2022/8/10 13:04
 * @Version 1.0
 **/
public class SleepUtil {
    public static void sleepSec(long time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleepMill(long time) {
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleepMin(long time) {
        try {
            TimeUnit.MINUTES.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
