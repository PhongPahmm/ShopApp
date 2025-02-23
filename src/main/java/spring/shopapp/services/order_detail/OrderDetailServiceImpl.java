package spring.shopapp.services.order_detail;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.shopapp.dtos.request.OrderDetailCreationRequest;
import spring.shopapp.dtos.response.OrderDetailResponse;
import spring.shopapp.exception.AppException;
import spring.shopapp.exception.ErrorCode;
import spring.shopapp.mapper.OrderDetailMapper;
import spring.shopapp.models.Order;
import spring.shopapp.models.OrderDetail;
import spring.shopapp.models.Product;
import spring.shopapp.repositories.OrderDetailRepository;
import spring.shopapp.repositories.OrderRepository;
import spring.shopapp.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderDetailServiceImpl implements OrderDetailService {
    OrderRepository orderRepository;
    ProductRepository productRepository;
    OrderDetailRepository orderDetailRepository;
    OrderDetailMapper orderDetailMapper;

    @Override
    public OrderDetailResponse createOrderDetail(OrderDetailCreationRequest request) {
        log.info(request.toString());
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(()-> new AppException(ErrorCode.ORDER_NOT_FOUND));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        OrderDetail orderDetail = orderDetailMapper.toOrderDetail(request);
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        log.info("OrderDetail created{}: ", orderDetail);
        return orderDetailMapper.toOrderDetailResponse(orderDetailRepository.save(orderDetail));
    }

    @Override
    public List<OrderDetailResponse> getOrderDetails() {
        return orderDetailRepository.findAll().stream()
                .map(orderDetailMapper::toOrderDetailResponse).toList();
    }
}
