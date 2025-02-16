package spring.shopapp.services.order_detail;

import spring.shopapp.dtos.request.OrderDetailCreationRequest;
import spring.shopapp.dtos.response.OrderDetailResponse;

import java.util.List;

public interface OrderDetailService {
    OrderDetailResponse createOrderDetail(OrderDetailCreationRequest request);
    List<OrderDetailResponse> getOrderDetails();
}
