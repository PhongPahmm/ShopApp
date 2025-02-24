package spring.shopapp.dtos.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import spring.shopapp.models.OrderStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    int id;
    int userId;
    LocalDateTime orderDate;
    OrderStatus orderStatus;
    float totalMoney;
}
