package spring.shopapp.dtos.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import spring.shopapp.models.OrderStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailCreationRequest {
    int orderId;
    int productId;
    float price;
    int numberOfProducts;
    float totalMoney;
    OrderStatus orderStatus;
}
