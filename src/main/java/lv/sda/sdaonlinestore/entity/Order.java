package lv.sda.sdaonlinestore.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "userName")
    @NotNull
    @Size(min=3, max=40)
    @Pattern(regexp = "[a-zA-Z0-9]{3,40}")
    private String userName;

    @Column(name = "totalCost")
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=6, fraction=2)
    private BigDecimal totalCost;

    @Column(name = "deliveryAddress")
    @NotNull
    private String deliveryAddress;

    @Column(name = "userAddress")
    @NotNull
    private String userAddress;

    @Column(name = "orderDate")
    private Date orderDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy="order")
    private Set<OrderLine> orderLines;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + orderId +
                ", userName='" + userName + '\'' +
                ", totalCost=" + totalCost +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderLines='" + orderLines + '\'' +
                ", status=" + status +
                '}';
    }
}
