package spring.shopapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spring.shopapp.dtos.request.OrderCreationRequest;
import spring.shopapp.dtos.response.OrderResponse;
import spring.shopapp.models.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toOrder(OrderCreationRequest request);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "product.id", target = "productId")
    OrderResponse toOrderResponse(Order order);

}
