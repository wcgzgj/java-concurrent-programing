package lock_and_condition.asynchronous_to_synchronous;


/**
 * @ClassName SyncCaller
 * @Description 调用同步方法
 * @Author faro_z
 * @Date 2022/7/18 01:37
 * @Version 1.0
 **/
public class SyncCaller {
    private static AsyncService service = new SyncService();
    public static void main(String[] args) throws Exception {
        if (service.doService()) {
            System.out.println(service.getResult());
        }
    }
}
