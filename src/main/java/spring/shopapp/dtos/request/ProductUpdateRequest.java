package spring.shopapp.dtos.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import spring.shopapp.models.ProductImage;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductUpdateRequest {
    int id;
    String name;
    float price;
    String thumbnail;
    String description;
    int categoryId;
    List<ProductImage> productImages;
}
