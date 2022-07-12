package thread.thread_life_time;

/**
 * @ClassName InterruptTimeWaiting
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/12 17:06
 * @Version 1.0
 **/
public class InterruptTimeWaiting {
    private static int count = 0;
    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                // 业务代码
                // ...
                try {
                    Thread.sleep(1000);
                    count = -1;
                } catch (InterruptedException e) {
                    // 手动添加终端标记，因为在 catch 后，会清除原先的中断标记
                    // 注释掉下面这行，程序就会进入死循环
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        });
        t.start();
        // 延后执行 interrupt(); 方法
        // 不然可能在一开始的 if 判断就跳出了
        Thread.sleep(200);
        t.interrupt();
        //t.join();
        System.out.println("count:"+count); // 0
    }
}
