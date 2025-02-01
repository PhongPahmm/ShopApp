package spring.shopapp.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import spring.shopapp.dtos.request.ApiResponse;
import spring.shopapp.dtos.request.FavoriteAddRequest;
import spring.shopapp.dtos.response.FavoriteResponse;
import spring.shopapp.services.favorite.FavoriteService;

import java.util.List;

@RestController
@RequestMapping("api/v1/favorites")
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
    @PostMapping
    public ApiResponse<FavoriteResponse> addFavorite(@RequestBody FavoriteAddRequest request){
        return ApiResponse.<FavoriteResponse>builder()
                .data(favoriteService.addFavorite(request))
                .message("Favorite added successfully")
                .build();
    }
}
