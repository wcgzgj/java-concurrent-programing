package cdl_and_cb.data;

import java.util.Objects;

/**
 * @ClassName Order
 * @Description 派送单实体类
 * @Author faro_z
 * @Date 2022/7/20 16:32
 * @Version 1.0
 **/
public class DeliveryOrder {
    private Long userId;
    private String userName;
    private String address;
    private Double price;

    public DeliveryOrder(Long userId, String userName, String address, Double price) {
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
        DeliveryOrder that = (DeliveryOrder) o;
        return Objects.equals(userId, that.userId) && Objects.equals(userName, that.userName) && Objects.equals(address, that.address) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, address, price);
    }

    @Override
    public String toString() {
        return "DeliveryOrder{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                '}';
    }
}
