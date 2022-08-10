package future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @ClassName SimpleFutureDemo
 * @Description TODO
 * @Author faro_z
 * @Date 2022/8/5 15:32
 * @Version 1.0
 **/
public class SimpleFutureDemo2 {
    private static final Logger logger = LoggerFactory.getLogger(SimpleFutureDemo2.class);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Result r = new Result("A");
        Future<?> future = executorService.submit(new MyTask(r));
        logger.info("等待 futuretask 结束...");
        long start = System.currentTimeMillis();
        System.out.println(future.isDone());
        while (!future.isDone()) {
            long curr = System.currentTimeMillis();
            logger.info("已经等待 {} 毫秒:",curr-start);
            TimeUnit.MILLISECONDS.sleep(40);
        };
        Object newR = future.get();
        System.out.println(newR);
        System.out.println(r==newR);
    }

    static class MyTask implements Runnable {

        private Result r;

        public MyTask(Result r) {
            this.r = r;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r.setVal2(r.getVal1());
        }
    }

    static class Result {
        private String val1;
        private String val2;

        public Result(String val1) {
            this.val1 = val1;
        }

        public String getVal1() {
            return val1;
        }

        public void setVal1(String val1) {
            this.val1 = val1;
        }

        public String getVal2() {
            return val2;
        }

        public void setVal2(String val2) {
            this.val2 = val2;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "val1='" + val1 + '\'' +
                    ", val2='" + val2 + '\'' +
                    '}';
        }
    }
}
