package spring.shopapp.services.order_detail;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import spring.shopapp.dtos.request.OrderDetailCreationRequest;
import spring.shopapp.dtos.response.OrderDetailResponse;
import spring.shopapp.mapper.OrderDetailMapper;
import spring.shopapp.models.OrderDetail;
import spring.shopapp.repositories.OrderDetailRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderDetailServiceImpl implements OrderDetailService {
    OrderDetailRepository orderDetailRepository;
    OrderDetailMapper orderDetailMapper;

    @Override
    public OrderDetailResponse createOrderDetail(OrderDetailCreationRequest request) {
        OrderDetail orderDetail = orderDetailMapper.toOrderDetail(request);
        return orderDetailMapper.toOrderDetailResponse(orderDetailRepository.save(orderDetail));
    }

    @Override
    public List<OrderDetailResponse> getOrderDetails() {
        return orderDetailRepository.findAll().stream()
                .map(orderDetailMapper::toOrderDetailResponse).toList();
    }
}
