package lock_and_condition.asynchronous_to_synchronous;

/**
 * @ClassName AsyCaller
 * @Description 调用异步方法
 * @Author faro_z
 * @Date 2022/7/18 00:59
 * @Version 1.0
 **/
public class AsyncCaller {


    public static void main(String[] args) throws Exception {
        AsyncService service = new AsyncService() {
            @Override
            void invokeCallBack(Response resp) {
                this.resp = resp;
            }
        };
        if (service.doService()) {
            System.out.println(service.getResult()); // null
            Thread.sleep(2100);
            System.out.println(service.getResult()); // Resp instance
        }
    }
}
