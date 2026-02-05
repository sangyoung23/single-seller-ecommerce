package com.ecommerce.single_seller_ecommerce.domain.order;

import com.ecommerce.single_seller_ecommerce.domain.product.ProductOption;
import com.ecommerce.single_seller_ecommerce.domain.product.ProductOptionRepository;
import com.ecommerce.single_seller_ecommerce.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductOptionRepository productOptionRepository;
    private final PaymentService paymentService;

    // 주문 생성
    public Order placeOrder(User user, Long productOptionId, int quantity, String paymentMethod) {

        // 1. 상품 옵션 조회
        ProductOption productOption = productOptionRepository.findById(productOptionId)
                .orElseThrow(() -> new IllegalArgumentException("상품 옵션 없음"));

        // 2. 주문 생성
        Order order = Order.builder()
                .user(user)
                .build();


        // 3. 주문 상품 생성
        OrderItem orderItem = OrderItem.builder()
                .productOption(productOption)
                .orderPrice(productOption.getExtraPrice())
                .quantity(quantity)
                .build();

        order.addOrderItem(orderItem);

        // 4. 주문 저장
        orderRepository.save(order);


        // 5. 결제 요청 (PaymentService에 위임)
        paymentService.requestPayment(order, paymentMethod);

        return order;
    }
}
