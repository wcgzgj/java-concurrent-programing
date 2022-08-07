package future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @ClassName FutureTaskDemo
 * @Description TODO
 * @Author faro_z
 * @Date 2022/8/7 16:18
 * @Version 1.0
 **/
public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        FutureTask<Integer> task = new FutureTask<>(() -> {
            return 1 + 2;
        });
        executorService.execute(task);
        System.out.println(task.get());
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
