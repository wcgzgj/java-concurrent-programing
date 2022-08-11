package completable_future;

import util.SleepUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @ClassName SimpleCompleatableDemo
 * @Description TODO
 * @Author faro_z
 * @Date 2022/8/10 13:02
 * @Version 1.0
 **/
public class SimpleCompletableDemo {
    public static void main(String[] args) {
        // runAsync 的编程接口是 Runnable ，没有返回值
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(()->{
            System.out.println("T1:洗茶壶...");
            SleepUtil.sleepSec(2);
            System.out.println("T1:烧开水..");
            SleepUtil.sleepSec(10);
        });

        // supplyAsync 要有返回值
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(()->{
            System.out.println("T2:洗茶壶...");
            SleepUtil.sleepSec(1);
            System.out.println("T2:洗茶杯...");
            SleepUtil.sleepSec(2);
            System.out.println("T2:拿茶叶...");
            SleepUtil.sleepSec(1);
            return "龙井";
        });

        // f1.thenCombine(f2, (re1, re2)->{}); 中
        // re1，re2 分别是 f1,f2 的返回值
        CompletableFuture<String> f3 = f1.thenCombine(f2, (re1, re2) -> {
            System.out.println("T1:拿到茶叶:" + re2);
            System.out.println("T1:泡茶...");
            return "上茶:" + re2;
        });

        System.out.println(f3.join());

    }



}
