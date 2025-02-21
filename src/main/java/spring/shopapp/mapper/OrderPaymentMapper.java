package spring.shopapp.mapper;

import org.mapstruct.Mapper;
import spring.shopapp.dtos.request.OrderPaymentRequest;
import spring.shopapp.dtos.response.OrderPaymentResponse;
import spring.shopapp.models.OrderPayment;

@Mapper(componentModel = "spring")
public interface OrderPaymentMapper {
    OrderPayment toOrderPayment(OrderPaymentRequest request);
    OrderPaymentResponse toOrderPaymentResponse(OrderPayment orderPayment);
}
