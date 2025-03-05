package spring.shopapp.services.favorite;

import spring.shopapp.dtos.response.FavoriteResponse;

import java.util.List;

public interface FavoriteService {
    FavoriteResponse addFavorite(int userId, int productId);
    List<FavoriteResponse> getFavorites();
    List<FavoriteResponse> getFavoritesByUserId(int userId);
    List<FavoriteResponse> deleteFavorite(int id);
}
