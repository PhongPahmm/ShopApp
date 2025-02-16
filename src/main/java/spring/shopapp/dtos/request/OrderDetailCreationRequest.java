package spring.shopapp.dtos.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailCreationRequest {
    int order_id;
    int product_id;
    float price;
    int numberOfProducts;
    float totalPrice;
    String color;
}
