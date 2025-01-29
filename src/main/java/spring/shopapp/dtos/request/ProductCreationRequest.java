package spring.shopapp.dtos.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreationRequest {
    String name;
    float price;
    MultipartFile thumbnail;
    String description;
    int categoryId;
    List<MultipartFile> productImages;
}
