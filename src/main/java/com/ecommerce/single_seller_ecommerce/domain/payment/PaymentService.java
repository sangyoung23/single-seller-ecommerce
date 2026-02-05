package com.ecommerce.single_seller_ecommerce.domain.payment;

import com.ecommerce.single_seller_ecommerce.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    // 결제 요청
    public Payment requestPayment(Order order, String paymentMethod) {
        Payment payment = Payment.builder()
                .order(order)
                .paymentMethod(paymentMethod)
                .build();

        paymentRepository.save(payment);

        return payment;
    }
}
