package future;


import java.util.concurrent.*;

/**
 * @ClassName FutureTaskRealUse
 * @Description 借助 futuretask 的特性，实现"筹运算"示例中的烧水沏茶
 *
 * 洗茶壶(2min)  -> 烧水（5min）     waiting                    -> 泡茶 -> 上茶
 *              -> 洗茶杯(3min) -> 擦桌子(3min) -> 拿茶叶(3min)
 * @Author faro_z
 * @Date 2022/8/7 16:29
 * @Version 1.0
 **/
public class FutureTaskRealUse {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        FutureTask<String> viceTask = new FutureTask<>(new ViceTask());
        FutureTask<String> mainTask = new FutureTask<>(new MainTask(viceTask));
        executorService.execute(mainTask);
        executorService.execute(viceTask);
        System.out.println(mainTask.get());
        executorService.shutdown();
    }

    static class MainTask implements Callable<String> {

        FutureTask<String> viceTask;

        public MainTask(FutureTask<String> viceTask) {
            this.viceTask = viceTask;
        }

        @Override
        public String call() throws Exception {
            System.out.println("主：洗茶壶");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("主：烧水");
            TimeUnit.SECONDS.sleep(5);
            String viceRes = viceTask.get();
            System.out.println(viceRes);
            System.out.println("主：开始泡茶....");
            return "上茶！！";
        }
    }

    static class ViceTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("副：洗茶杯");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("副：擦桌子");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("副：拿茶叶");
            TimeUnit.SECONDS.sleep(2);
            return "泡茶前的准备工作已经完成了";
        }
    }
}
