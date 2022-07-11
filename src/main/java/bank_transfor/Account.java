package bank_transfor;

import java.util.Objects;
import java.util.Random;

/**
 * @ClassName Account
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/8 17:05
 * @Version 1.0
 **/
public class Account {
    private Long id;
    private String userName;
    private Long amount;

    public Account() {
    }

    public Account(Long id, String userName, Long amount) {
        this.id = id;
        this.userName = userName;
        this.amount = amount;
    }

    public boolean transfor(Account to,Long amount) {
        if (this.amount<amount) {
            return false;
        }
        this.setAmount(this.getAmount()-amount);
        // 模拟转账时出现的延迟
        try {
            Thread.sleep(new Random().nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        to.setAmount(to.getAmount()+amount);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(userName, account.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName);
    }
}
