package spring.shopapp.dtos.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import spring.shopapp.models.PaymentMethod;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    int id;
    int userId;
    int productId;
    int quantity;
    LocalDateTime orderDate;
    String shippingAddress;
    PaymentMethod paymentMethod;
    float totalMoney;
}
