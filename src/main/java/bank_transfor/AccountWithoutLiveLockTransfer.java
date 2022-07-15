package bank_transfor;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName AccountWithLiveLockTransfer
 * @Description 解决活锁（在释放锁前，随机等待一段时间）
 * @Author faro_z
 * @Date 2022/7/15 18:45
 * @Version 1.0
 **/
public class AccountWithoutLiveLockTransfer {

    private Long id;
    private String userName;
    private Long amount;
    public final ReentrantLock lock = new ReentrantLock();
    private final Random r = new Random();

    public AccountWithoutLiveLockTransfer(Long id, String userName, Long amount) {
        this.id = id;
        this.userName = userName;
        this.amount = amount;
    }

    public boolean transfer(AccountWithoutLiveLockTransfer to, Long amount) {

        boolean doLoop = true;
        while (doLoop) {
            // 非阻塞获取锁
            // 外层成功，内层失败，直接走 finally 释放外层锁，防止死锁的产生
            // 但是如果 A,B  和  B,A 的资源在同时被两个线程尝试获取，会发生"活锁"问题
            if (this.lock.tryLock()) {
                try {
                    // 让等待下一个锁的时间随机，避开活锁
                    if (to.lock.tryLock(r.nextInt(100), TimeUnit.MILLISECONDS)) {
                        try {
                            if (this.amount<amount) {
                                return false;
                            }
                            this.setAmount(this.getAmount()-amount);
                            to.setAmount(to.getAmount()+amount);
                            doLoop = false;
                        } finally {
                            to.lock.unlock();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    this.lock.unlock();
                }
            }
        }
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
