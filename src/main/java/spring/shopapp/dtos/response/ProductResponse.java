package spring.shopapp.dtos.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import spring.shopapp.models.Category;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    int id;
    String name;
    float price;
    String thumbnail;
    String description;
    Category category;
}
