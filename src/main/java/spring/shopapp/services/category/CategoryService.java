package spring.shopapp.services.category;

import spring.shopapp.dtos.request.CategoryCreationRequest;
import spring.shopapp.dtos.request.CategoryUpdateRequest;
import spring.shopapp.dtos.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory (CategoryCreationRequest request);
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(int id);
    CategoryResponse updateCategory(int id, CategoryUpdateRequest request);
    Void deleteCategory(int id);
}
