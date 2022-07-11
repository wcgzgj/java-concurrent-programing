package bank_transfor;

/**
 * @ClassName Bank
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/8 17:20
 * @Version 1.0
 **/
public class Bank {
    private Allocator allocator = Allocator.getInstance();
    public boolean transfor(Account from, Account to,Long amount) {
        // 当无法在分配器中获取所需要的两个对象时，阻塞
        // 这里后面可以用 notify 和 wait 进行优化，减少 CPU 的资源占用
        allocator.apply(from,to);
        try {
            synchronized (from) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (to) {
                    if (!from.transfor(to,amount)) {
                        // 余额不足，转账失败
                        return false;
                    }
                }
            }
        } finally {
            allocator.free(from,to);
        }
        return true;
    }
}
