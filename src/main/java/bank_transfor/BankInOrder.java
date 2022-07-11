package bank_transfor;

/**
 * @ClassName BankInOrder
 * @Description
 * @Author faro_z
 * @Date 2022/7/9 15:35
 * @Version 1.0
 **/
public class BankInOrder {
    public boolean transfor(Account from, Account to,Long amount) {
        Account a1 = from;
        Account a2 = to;
        if (a1.getId()>a2.getId()) {
            Account tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        synchronized (a1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (a2) {
                if (!from.transfor(to,amount)) {
                    // 余额不足，转账失败
                    return false;
                }
            }
        }
        return true;
    }
}
