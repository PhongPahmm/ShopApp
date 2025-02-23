package spring.shopapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spring.shopapp.dtos.request.OrderDetailCreationRequest;
import spring.shopapp.dtos.response.OrderDetailResponse;
import spring.shopapp.models.OrderDetail;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    OrderDetail toOrderDetail(OrderDetailCreationRequest request);
    @Mapping(source = "order.id", target = "order_id")
    @Mapping(source = "product.id", target = "product_id")
    OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail);

}
