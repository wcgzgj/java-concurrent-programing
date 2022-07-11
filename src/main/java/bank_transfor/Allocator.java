package bank_transfor;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Allocator
 * @Description 让转账操作一次获取所有的对象锁，打破"占有且等待"的死锁必要条件，避免死锁
 * @Author faro_z
 * @Date 2022/7/8 17:04
 * @Version 1.0
 **/
public class Allocator {
    // 里面存着的是正在被处理的账户信息
    private Set<Account> underHandledAccounts = new HashSet<>();
    private static Allocator instance = new Allocator();

    private Allocator() {}

    public static Allocator getInstance() {
        return instance;
    }

    /**
     * 申请资源
     * @param account1
     * @param account2
     */
    public synchronized void apply(Account account1,Account account2) {
        try {
            while (underHandledAccounts.contains(account1) ||
                    underHandledAccounts.contains(account2)) {
                wait();
            }
            underHandledAccounts.add(account1);
            underHandledAccounts.add(account2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放资源
     * @param account1
     * @param account2
     */
    public synchronized void free(Account account1,Account account2) {
        underHandledAccounts.remove(account1);
        underHandledAccounts.remove(account2);
        notifyAll();
    }
}
