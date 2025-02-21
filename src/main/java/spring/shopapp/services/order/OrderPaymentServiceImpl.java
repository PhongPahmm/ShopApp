package spring.shopapp.services.order;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import spring.shopapp.dtos.request.OrderPaymentRequest;
import spring.shopapp.dtos.response.OrderPaymentResponse;
import spring.shopapp.mapper.OrderPaymentMapper;
import spring.shopapp.models.OrderPayment;
import spring.shopapp.repositories.OrderPaymentRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderPaymentServiceImpl implements OrderPaymentService {
    OrderPaymentRepository orderPaymentRepository;
    OrderPaymentMapper orderPaymentMapper;


    @Override
    public OrderPaymentResponse createOrderPayment(OrderPaymentRequest request) {
        OrderPayment orderPayment = orderPaymentMapper.toOrderPayment(request);
        return orderPaymentMapper.toOrderPaymentResponse(orderPaymentRepository.save(orderPayment));
    }

    @Override
    public OrderPaymentResponse getOrderPayment(int id) {
        OrderPayment orderPayment = orderPaymentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Order payment not found"));
        return orderPaymentMapper.toOrderPaymentResponse(orderPayment);
    }
}
