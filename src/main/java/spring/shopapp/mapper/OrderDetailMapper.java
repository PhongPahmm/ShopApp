package spring.shopapp.mapper;

import org.mapstruct.Mapper;
import spring.shopapp.dtos.request.OrderCreationRequest;
import spring.shopapp.dtos.request.OrderDetailCreationRequest;
import spring.shopapp.dtos.response.OrderDetailResponse;
import spring.shopapp.dtos.response.OrderResponse;
import spring.shopapp.models.Order;
import spring.shopapp.models.OrderDetail;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    OrderDetail toOrderDetail(OrderDetailCreationRequest request);
    OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail);

}
