package cdl_and_cb.data;

import javax.swing.*;
import java.util.Objects;

/**
 * @ClassName Diff
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/20 17:00
 * @Version 1.0
 **/
public class Diff {
    private Long userId;
    private String userName;
    private String address;
    private String price;

    public Diff(Long userId) {
        this.userId = userId;
    }

    public Diff(Long userId, String userName, String address, String price) {
        this.userId = userId;
        this.userName = userName;
        this.address = address;
        this.price = price;
    }

    public boolean hasDiff() {
        return !(this.userId==null && this.userName==null && this.address==null && this.price==null);
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diff diff = (Diff) o;
        return Objects.equals(userId, diff.userId) && Objects.equals(userName, diff.userName) && Objects.equals(address, diff.address) && Objects.equals(price, diff.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, address, price);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Diff{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
