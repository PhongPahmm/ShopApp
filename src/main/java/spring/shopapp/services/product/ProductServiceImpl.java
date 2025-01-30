package spring.shopapp.services.product;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import spring.shopapp.dtos.request.ProductCreationRequest;
import spring.shopapp.dtos.request.ProductUpdateRequest;
import spring.shopapp.dtos.response.ProductResponse;
import spring.shopapp.exception.AppException;
import spring.shopapp.exception.ErrorCode;
import spring.shopapp.mapper.ProductMapper;
import spring.shopapp.models.Category;
import spring.shopapp.models.Product;
import spring.shopapp.models.ProductImage;
import spring.shopapp.repositories.CategoryRepository;
import spring.shopapp.repositories.ProductRepository;
import spring.shopapp.services.file_store.FileStorageService;

import java.util.List;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ProductMapper productMapper;
    FileStorageService fileStorageService;

    @Override
    public ProductResponse createProduct(ProductCreationRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId() )
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        // Upload thumbnail và lấy URL
        String thumbnailUrl = fileStorageService.store(request.getThumbnail());

        // Ánh xạ các trường cơ bản từ request sang Product
        Product product = productMapper.toProduct(request);
        product.setThumbnail(thumbnailUrl);
        product.setCategory(category);

        // Xử lý upload các file ảnh và tạo ProductImage
        List<ProductImage> productImages = request.getProductImages().stream()
                .map(file -> {
                    String imageUrl = fileStorageService.store(file);
                    ProductImage image = new ProductImage();
                    image.setImageUrl(imageUrl);
                    image.setProduct(product);
                    return image;
                })
                .toList();
        product.setProductImages(productImages);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse getProductById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        return productMapper.toProductResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::toProductResponse).toList();
    }

    @Override
    public ProductResponse updateProduct(int id, ProductUpdateRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId() )
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        product.setCategory(category);
        productMapper.updateProduct(product, request);

        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public List<ProductResponse> deleteProduct(int id) {
        if(!productRepository.existsById(id)) {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        productRepository.deleteById(id);
        var remainProducts = productRepository.findAll();
        return remainProducts.stream().map(productMapper::toProductResponse).toList();
    }
}
