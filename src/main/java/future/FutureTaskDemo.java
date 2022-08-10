package future;


import java.util.concurrent.*;

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
        Result param = new Result("faroz");
        MyTask task = new MyTask(param);
        // FutureTask 是 Future 接口的实现
        // 和 Future 接口的使用区别在于
        // Future 是 executorService.submit(); 返回值的引用
        // 而 FutureTask 作为实例对象，可以单独作为引用
        // 类比 ArrayList 和 List
        FutureTask<Result> ft = new FutureTask<>(task);
        executorService.submit(ft);
        Result result = ft.get();
        System.out.println(result);
        System.out.println(result==param);
    }

    static class MyTask implements Callable<Result> {

        private Result r;

        public MyTask(Result r) {
            this.r = r;
        }

        @Override
        public Result call() throws Exception {
            r.setVal2(r.getVal1());
            return r;
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
