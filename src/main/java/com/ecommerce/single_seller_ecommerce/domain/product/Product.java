package com.ecommerce.single_seller_ecommerce.domain.product;

import com.ecommerce.single_seller_ecommerce.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id; // 기본 키

    @Column(nullable = false, length = 50)
    private String name; // 상품명

    @Column(nullable = false)
    private Long price; // 상품 가격

    @Column(length = 255)
    private String description; // 상품 설명

    @Column(nullable = false)
    private int stockQuantity; // 상품 재고 수량

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ProductStatus status; // 상품 상태

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOption> options = new ArrayList<>(); // 상품 옵션

    @Builder
    public Product(String name, Long price, String description, int stockQuantity, ProductStatus status) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stockQuantity = stockQuantity;
        this.status = status != null ? status : ProductStatus.ON_SALE;
    }

    public void addOption(ProductOption option) {
        options.add(option);
        option.assignProduct(this);
    }
}
