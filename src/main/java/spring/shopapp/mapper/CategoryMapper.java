package spring.shopapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import spring.shopapp.dtos.request.CategoryCreationRequest;
import spring.shopapp.dtos.request.CategoryUpdateRequest;
import spring.shopapp.dtos.response.CategoryResponse;
import spring.shopapp.models.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory (CategoryCreationRequest request);
    CategoryResponse toCategoryResponse(Category category);
    void updateCategory (CategoryUpdateRequest request, @MappingTarget Category category);
}
