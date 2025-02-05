package spring.shopapp.mapper;

import org.mapstruct.Mapper;
import spring.shopapp.dtos.request.OrderCreationRequest;
import spring.shopapp.dtos.response.OrderResponse;
import spring.shopapp.models.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toOrder(OrderCreationRequest request);
    OrderResponse toOrderResponse(Order order);

}
