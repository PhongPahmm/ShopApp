package spring.shopapp.services.order;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.shopapp.dtos.request.OrderCreationRequest;
import spring.shopapp.dtos.response.OrderResponse;
import spring.shopapp.exception.AppException;
import spring.shopapp.exception.ErrorCode;
import spring.shopapp.mapper.OrderMapper;
import spring.shopapp.models.Order;
import spring.shopapp.models.PaymentMethod;
import spring.shopapp.models.Product;
import spring.shopapp.models.User;
import spring.shopapp.repositories.OrderRepository;
import spring.shopapp.repositories.ProductRepository;
import spring.shopapp.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;
    OrderMapper orderMapper;
    UserRepository userRepository;
    ProductRepository productRepository;

    @Override
    public OrderResponse createOrder(OrderCreationRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        float totalPrice = product.getPrice() * request.getQuantity();
        Order order = orderMapper.toOrder(request);
        order.setUser(user);
        order.setProduct(product);
        order.setTotalMoney(totalPrice);
        order.setPaymentMethod(PaymentMethod.CASH);

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
