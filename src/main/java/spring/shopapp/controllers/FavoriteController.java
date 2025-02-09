package spring.shopapp.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import spring.shopapp.dtos.request.ApiResponse;
import spring.shopapp.dtos.response.FavoriteResponse;
import spring.shopapp.services.favorite.FavoriteService;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FavoriteController {
    FavoriteService favoriteService;

    @GetMapping
    public ApiResponse<List<FavoriteResponse>> getFavorites() {
        return ApiResponse.<List<FavoriteResponse>>builder()
                .data(favoriteService.getFavorites())
                .build();
    }

    @GetMapping("/{userId}")
    public ApiResponse<List<FavoriteResponse>> getFavoriteByUserId(@PathVariable int userId) {
        return ApiResponse.<List<FavoriteResponse>>builder()
                .data(favoriteService.getFavoritesByUserId(userId))
                .build();
    }

    @PostMapping("/{userId}/{productId}")
    public ApiResponse<FavoriteResponse> addFavorite(@PathVariable int userId,
                                                     @PathVariable int productId){
        return ApiResponse.<FavoriteResponse>builder()
                .data(favoriteService.addFavorite(userId, productId))
                .message("Favorite added successfully")
                .build();
    }
}
