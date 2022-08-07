package future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName SimpleFutureDemo
 * @Description TODO
 * @Author faro_z
 * @Date 2022/8/5 15:32
 * @Version 1.0
 **/
public class SimpleFutureDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Result r = new Result("A");
        Future<?> future = executorService.submit(new MyTask(r));
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
