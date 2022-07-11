package bank_transfor;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName AransforDemo1
 * @Description 测试避免 "占有且等待"的死锁必要条件 的并发转账操作
 * @Author faro_z
 * @Date 2022/7/8 17:50
 * @Version 1.0
 **/
public class TransforDemo1 {

    private static final Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        Account a1 = new Account(1l, "JOJO", 2000l);
        Account a2 = new Account(2l, "DIO", 2000l);
        Account a3 = new Account(3l, "FARO_Z", 2000l);
        Account a4 = new Account(4l, "KKK", 2000l);
        Account a5 = new Account(5l, "ZZZ", 2000l);
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(a1);
        accounts.add(a2);
        accounts.add(a3);
        accounts.add(a4);
        accounts.add(a5);
        List<TransforAction> actions = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int fromIndex = r.nextInt(accounts.size());
            int toIndex = r.nextInt(accounts.size());
            while (toIndex==fromIndex) {
                toIndex = r.nextInt(accounts.size());
            }
            actions.add(new TransforAction(bank,accounts.get(fromIndex),accounts.get(toIndex),new Long(r.nextInt(1000))));
        }
        System.out.println("开始执行所有转账操作...");
        for (TransforAction action : actions) {
            action.start();
        }
        //for (TransforAction action : actions) {
        //    action.join();
        //}
        System.out.println("所有转账操作结束！");
        System.out.println("账户总余额为："+checkTotalAmount(accounts));
    }

    /**
     * 统计所有用户余额总和
     * @param accounts
     * @return
     */
    public static long checkTotalAmount(List<Account> accounts) {
        long sum = 0l;
        for (Account account : accounts) {
            sum+=account.getAmount();
        }
        return sum;
    }

    static class TransforAction extends Thread {

        private Bank bank;
        private Account from;
        private Account to;
        private Long amount;

        public TransforAction(Bank bank, Account from, Account to, Long amount) {
            this.bank = bank;
            this.from = from;
            this.to = to;
            this.amount = amount;
        }

        @Override
        public void run() {
            System.out.println(from.getUserName()+"->"+to.getUserName()+"开始转账...");
            System.out.println("转账金额:"+this.amount);
            System.out.println(from.getUserName()+":"+from.getAmount());
            System.out.println(to.getUserName()+":"+to.getAmount());
            bank.transfor(from,to,amount);
            System.out.println(from.getUserName()+"->"+to.getUserName()+"转账结束！");
            System.out.println(from.getUserName()+":"+from.getAmount());
            System.out.println(to.getUserName()+":"+to.getAmount());
            System.out.println("");
        }
    }
}
