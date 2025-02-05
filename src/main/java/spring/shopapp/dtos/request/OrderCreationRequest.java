package spring.shopapp.dtos.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import spring.shopapp.models.OrderStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderCreationRequest {
    LocalDateTime orderDate;
    OrderStatus orderStatus;
    float totalPrice;
}
