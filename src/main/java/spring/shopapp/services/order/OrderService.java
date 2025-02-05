package spring.shopapp.services.order;

import spring.shopapp.dtos.request.OrderCreationRequest;
import spring.shopapp.dtos.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderCreationRequest request);
    List<OrderResponse> getAllOrders();
    OrderResponse getOrderById(int id);
}
