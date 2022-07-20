package cdl_and_cb.data;

import java.util.Objects;

/**
 * @ClassName PreOrder
 * @Description 预订单实体类
 * @Author faro_z
 * @Date 2022/7/20 16:33
 * @Version 1.0
 **/
public class PreOrder {
    private Long userId;
    private String userName;
    private String address;
    private Double price;

    public PreOrder(Long userId, String userName, String address, Double price) {
        this.userId = userId;
        this.userName = userName;
        this.address = address;
        this.price = price;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreOrder preOrder = (PreOrder) o;
        return Objects.equals(userId, preOrder.userId) && Objects.equals(userName, preOrder.userName) && Objects.equals(address, preOrder.address) && Objects.equals(price, preOrder.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, address, price);
    }

    @Override
    public String toString() {
        return "PreOrder{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                '}';
    }
}
