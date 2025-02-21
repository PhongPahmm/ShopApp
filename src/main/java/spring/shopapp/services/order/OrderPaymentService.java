package spring.shopapp.services.order;

import spring.shopapp.dtos.request.OrderPaymentRequest;
import spring.shopapp.dtos.response.OrderPaymentResponse;

public interface OrderPaymentService {
    OrderPaymentResponse createOrderPayment(OrderPaymentRequest request);
    OrderPaymentResponse getOrderPayment(int id);
}
