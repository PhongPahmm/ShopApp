package spring.shopapp.services.order;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import spring.shopapp.dtos.request.OrderCreationRequest;
import spring.shopapp.dtos.response.OrderResponse;
import spring.shopapp.exception.AppException;
import spring.shopapp.exception.ErrorCode;
import spring.shopapp.mapper.OrderMapper;
import spring.shopapp.models.Order;
import spring.shopapp.models.OrderStatus;
import spring.shopapp.repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;
    OrderMapper orderMapper;
    @Override
    public OrderResponse createOrder(OrderCreationRequest request) {
        Order order = orderMapper.toOrder(request);
       return orderMapper.toOrderResponse(orderRepository.save(order));
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::toOrderResponse).toList();
    }

    @Override
    public OrderResponse getOrderById(int id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.ORDER_NOT_FOUND));
        return orderMapper.toOrderResponse(order);
    }
}
