package com.ecommerce.single_seller_ecommerce.domain.product;

import com.ecommerce.single_seller_ecommerce.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_product_options")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_option_id")
    private Long id; // 기본 키

    @Column(nullable = false, length = 50)
    private String optionName; // 옵션 이름

    @Column(nullable = false, length = 50)
    private String optionValue; // 옵션 값

    @Column(nullable = false)
    private int extraPrice; // 옵션 가격

    @Column(nullable = false)
    private int stock; // 옵션 수량

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Builder
    public ProductOption(String optionName, String optionValue, int extraPrice, int stock) {
        this.optionName = optionName;
        this.optionValue = optionValue;
        this.extraPrice = extraPrice;
        this.stock = stock;
    }

    public void assignProduct(Product product) {
        this.product = product;
    }

    public void decreaseStock(int quantity) {
        if (stock < quantity) {
            throw new IllegalStateException("옵션 재고 부족");
        }
        stock -= quantity;
    }

}
