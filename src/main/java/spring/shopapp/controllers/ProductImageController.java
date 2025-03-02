package spring.shopapp.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import spring.shopapp.services.product.product_image.ProductImageService;

@RestController
@RequestMapping("/product/image")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductImageController{
    ProductImageService productImageService;
    @DeleteMapping("/{id}")
    public void deleteProductImage(@PathVariable int id) {
        productImageService.deleteProductImage(id);
    }
}
