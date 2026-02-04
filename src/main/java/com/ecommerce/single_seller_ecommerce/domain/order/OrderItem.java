package com.ecommerce.single_seller_ecommerce.domain.order;

import com.ecommerce.single_seller_ecommerce.domain.product.ProductOption;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_order_item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id; // 기본 키

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // 주문

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_option_id", nullable = false)
    private ProductOption productOption; // 상품 옵션 (구체화 된 상품)

    @Column(nullable = false)
    private int orderPrice; // 주문 가격

    @Column(nullable = false)
    private int quantity; // 수량

    public int getTotalPrice() {
        return orderPrice * quantity;
    }

    public void assignOrder(Order order) {
        this.order = order;
    }

    public void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("수량은 1 이상이어야 합니다.");
        }
    }

    @Builder
    public OrderItem(ProductOption productOption, int orderPrice, int quantity) {
        this.productOption = productOption;
        this.orderPrice = orderPrice;
        this.quantity = quantity;
    }

}
