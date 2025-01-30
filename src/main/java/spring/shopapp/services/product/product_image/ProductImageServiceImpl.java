package spring.shopapp.services.product.product_image;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import spring.shopapp.models.ProductImage;
import spring.shopapp.repositories.ProductImageRepository;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductImageServiceImpl implements ProductImageService {
    ProductImageRepository productImageRepository;

    @Override
    public void deleteProductImage(int productImageId) {
        ProductImage productImage = productImageRepository.findById(productImageId)
                .orElseThrow(()-> new RuntimeException("Invalid product image"));
        productImageRepository.delete(productImage);
    }
}
