package spring.shopapp.services.category;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import spring.shopapp.dtos.request.CategoryCreationRequest;
import spring.shopapp.dtos.request.CategoryUpdateRequest;
import spring.shopapp.dtos.response.CategoryResponse;
import spring.shopapp.exception.AppException;
import spring.shopapp.exception.ErrorCode;
import spring.shopapp.mapper.CategoryMapper;
import spring.shopapp.models.Category;
import spring.shopapp.repositories.CategoryRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;
    @Override
    public CategoryResponse createCategory(CategoryCreationRequest request) {
        Category category = categoryMapper.toCategory(request);
        if(categoryRepository.existsByName(category.getName())) {
            throw new AppException(ErrorCode.CATEGORY_EXISTED);
        }
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::toCategoryResponse).toList();
    }

    @Override
    public CategoryResponse getCategoryById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(int id, CategoryUpdateRequest request) {
        Category category = categoryRepository.findById(request.getId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        categoryMapper.updateCategory(request, category);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    @Override
    public Void deleteCategory(int id) {
        categoryRepository.deleteById(id);
        var remainCategory = categoryRepository.findAll();
        return null;
    }
}
