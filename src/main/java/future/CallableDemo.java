package future;

import java.util.concurrent.Callable;

/**
 * @ClassName CallableDemo
 * @Description TODO
 * @Author faro_z
 * @Date 2022/8/7 16:20
 * @Version 1.0
 **/
public class CallableDemo {
    public static void main(String[] args) throws Exception {
        MyCallable call = new MyCallable();
        System.out.println(call.call());
    }

    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(2000);
            return "Thread End!";
        }
    }
}
