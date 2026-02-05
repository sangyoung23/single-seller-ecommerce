package com.ecommerce.single_seller_ecommerce.domain.payment;

import com.ecommerce.single_seller_ecommerce.domain.order.Order;
import com.ecommerce.single_seller_ecommerce.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_payments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id; // 기본 키

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // 주문

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus status; // 결제 상태

    @Column(nullable = false, length = 25)
    private String paymentMethod; // 결제 수단

    private LocalDateTime requestedAt; // 결제 요청 시각

    private LocalDateTime approvedAt; // 실제 결제 승인 시각

    public void approve() {
        if (this.status != PaymentStatus.REQUESTED) {
            throw new IllegalStateException("결제 요청 상태에서만 승인할 수 있습니다.");
        }

        this.status = PaymentStatus.APPROVED;
        this.approvedAt = LocalDateTime.now();
    }

    public void fail() {
        this.status = PaymentStatus.FAILED;
    }

    public void cancel() {
        if (this.status != PaymentStatus.REQUESTED && this.status != PaymentStatus.APPROVED) {
            throw new IllegalStateException("취소할 수 없는 결제 상태입니다.");
        }

        this.status = PaymentStatus.CANCELLED;
    }

    @Builder
    public Payment(Order order, String paymentMethod) {
        this.order = order;
        this.status = PaymentStatus.REQUESTED;
        this.paymentMethod = paymentMethod;
        this.requestedAt = LocalDateTime.now();
    }
}
