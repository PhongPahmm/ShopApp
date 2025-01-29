package spring.shopapp.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import spring.shopapp.dtos.request.ApiResponse;
import spring.shopapp.dtos.request.ProductCreationRequest;
import spring.shopapp.dtos.request.ProductUpdateRequest;
import spring.shopapp.dtos.response.ProductResponse;
import spring.shopapp.services.product.ProductService;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;

    @PostMapping(value = "/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<ProductResponse> createProduct(@ModelAttribute ProductCreationRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .data(productService.createProduct(request))
                .message("Product created successfully")
                .build();
    }

    @GetMapping("/product")
    public ApiResponse<List<ProductResponse>> getAllProducts() {
        return ApiResponse.<List<ProductResponse>>builder()
                .data(productService.getAllProducts())
                .message("Get all products successfully")
                .build();
    }

    @GetMapping("/product/{productId}")
    public ApiResponse<ProductResponse> getProductById(@PathVariable("productId") int productId) {
        return ApiResponse.<ProductResponse>builder()
                .data(productService.getProductById(productId))
                .message("Get product successfully")
                .build();
    }

    @PutMapping("/product/{productId}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable("productId") int productId, @RequestBody ProductUpdateRequest request){
        return ApiResponse.<ProductResponse>builder()
                .data(productService.updateProduct(productId, request))
                .message("Product updated successfully")
                .build();
    }

    @DeleteMapping("/product/{productId}")
    public ApiResponse<List<ProductResponse>> deleteProduct(@PathVariable("productId") int productId){
        return ApiResponse.<List<ProductResponse>>builder()
                .data(productService.deleteProduct(productId))
                .message("Product deleted successfully")
                .build();
    }
}
