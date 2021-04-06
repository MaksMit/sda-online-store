package lv.sda.sdaonlinestore.entity;

import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="order_lines")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderLineId")
    private Long orderLineId;

    @Column(name = "product")
    @NotNull
    private String product;

    @Column(name = "quantity")
    @NotNull
    private Integer quantity;

    @Column(name = "productPrice")
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=6, fraction=2)
    private BigDecimal productPrice;

    @ManyToOne
    @JoinColumn(name="orderId")
    private Order order;

    public Long getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(Long orderLineId) {
        this.orderLineId = orderLineId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + orderLineId +
                ", product='" + product + '\'' +
                ", quantity=" + quantity +
                ", productPrice=" + productPrice +
                '}';
    }
}
