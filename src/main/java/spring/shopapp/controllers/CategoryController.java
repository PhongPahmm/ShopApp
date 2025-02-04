package spring.shopapp.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import spring.shopapp.dtos.request.ApiResponse;
import spring.shopapp.dtos.request.CategoryCreationRequest;
import spring.shopapp.dtos.request.CategoryUpdateRequest;
import spring.shopapp.dtos.response.CategoryResponse;
import spring.shopapp.services.category.CategoryService;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryService categoryService;

    @PostMapping("")
    public ApiResponse<CategoryResponse> createCategory(@RequestBody CategoryCreationRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .message("Successfully created new category")
                .data(categoryService.createCategory(request))
                .build();
    }

    @GetMapping("")
    public ApiResponse<List<CategoryResponse>> getAllCategories() {
        return ApiResponse.<List<CategoryResponse>>builder()
                .message("Successfully retrieved all categories")
                .data(categoryService.getAllCategories())
                .build();
    }

    @GetMapping("/{categoryId}")
    public ApiResponse<CategoryResponse> getCategoryById(@PathVariable int categoryId) {
        return ApiResponse.<CategoryResponse>builder()
                .message("Successfully retrieved category by id")
                .data(categoryService.getCategoryById(categoryId))
                .build();
    }

    @PutMapping("/{categoryId}")
    public ApiResponse<CategoryResponse> updateCategory(@PathVariable int categoryId, @RequestBody CategoryUpdateRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .message("Successfully updated category")
                .data(categoryService.updateCategory(categoryId, request))
                .build();
    }

    @DeleteMapping("/{categoryId}")
    public ApiResponse<List<CategoryResponse>> deleteCategory(@PathVariable int categoryId) {
        return ApiResponse.<List<CategoryResponse>>builder()
                .data(categoryService.deleteCategory(categoryId))
                .message("Category deleted successfully")
                .build();
    }
}
