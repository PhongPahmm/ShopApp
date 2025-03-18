package spring.shopapp.dtos.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import spring.shopapp.models.PaymentMethod;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderCreationRequest {
    int userId;
    int productId;
    int quantity;
    LocalDateTime orderDate;
    String shippingAddress;
    PaymentMethod paymentMethod;
}
