package com.ecommerce.single_seller_ecommerce.domain.order;

public enum OrderStatus {
    CREATED, // 주문 생성
    PAYMENT_PENDING, // 결제 대기
    PAID, // 결제 완료
    PAYMENT_FAILED, // 결제 실패
    CANCELLED, // 주문 취소
    SHIPPED, // 배송 중
    COMPLETED, // 배송 완료
}
