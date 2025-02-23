package spring.shopapp.dtos.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailResponse {
    int id;
    int order_id;
    int product_id;
    float price;
    int numberOfProducts;
    float totalMoney;
    String color;
}
