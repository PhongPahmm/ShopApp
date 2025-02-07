package spring.shopapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import spring.shopapp.dtos.request.ProductCreationRequest;
import spring.shopapp.dtos.request.ProductUpdateRequest;
import spring.shopapp.dtos.response.ProductResponse;
import spring.shopapp.models.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(target = "thumbnail", ignore = true)
    @Mapping(target = "productImages", ignore = true)
    Product toProduct(ProductCreationRequest request);

    ProductResponse toProductResponse(Product product);
    @Mapping(target = "id", ignore = true)
    void updateProduct(@MappingTarget Product product, ProductUpdateRequest request);
}


