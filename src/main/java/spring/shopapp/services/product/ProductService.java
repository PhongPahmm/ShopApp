package spring.shopapp.services.product;

import spring.shopapp.dtos.request.ProductCreationRequest;
import spring.shopapp.dtos.request.ProductUpdateRequest;
import spring.shopapp.dtos.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductCreationRequest request);
    ProductResponse getProductById(int id);
    List<ProductResponse> getAllProducts();
    ProductResponse updateProduct(int id, ProductUpdateRequest request);
    List<ProductResponse> deleteProduct(int id);

}
