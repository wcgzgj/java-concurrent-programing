package future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.stream.StreamSupport;

/**
 * @ClassName SimpleFutureDemo3CallableParam
 * @Description TODO
 * @Author faro_z
 * @Date 2022/8/10 11:39
 * @Version 1.0
 **/
public class SimpleFutureDemo3CallableParam {
    private static final Logger logger = LoggerFactory.getLogger(SimpleFutureDemo3CallableParam.class);
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Result param = new Result("faroz");
        MyTask task = new MyTask(param);
        Future<Result> future = executorService.submit(task);
        logger.info("开始执行子程序...");
        long start = System.currentTimeMillis();
        while (!future.isDone()) {
            long curr = System.currentTimeMillis();
            logger.info("已经等待 {} 毫秒:",curr-start);
            TimeUnit.MILLISECONDS.sleep(100);
        }
        Result res = task.call();
        System.out.println(res); // {val1='faroz', val2='faroz'}
        logger.info(String.valueOf(res==param)); // true
    }

    static class MyTask implements Callable<Result> {

        private Result r;

        public MyTask(Result r) {
            this.r = r;
        }

        @Override
        public Result call() throws Exception {
            TimeUnit.SECONDS.sleep(1);
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
