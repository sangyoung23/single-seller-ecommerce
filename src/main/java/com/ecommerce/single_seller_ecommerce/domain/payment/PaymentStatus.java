package com.ecommerce.single_seller_ecommerce.domain.payment;

public enum PaymentStatus {
    REQUESTED,   // 결제 요청
    APPROVED,    // 결제 승인
    FAILED,      // 실패
    CANCELLED   // 취소
    }
