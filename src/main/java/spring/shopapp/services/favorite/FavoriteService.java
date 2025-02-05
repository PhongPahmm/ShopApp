package spring.shopapp.services.favorite;

import spring.shopapp.dtos.response.FavoriteResponse;
import spring.shopapp.models.Favorite;

import java.util.List;

public interface FavoriteService {
    FavoriteResponse addFavorite(int userId, int productId);
    List<FavoriteResponse> getFavorites();
    List<FavoriteResponse> getFavoritesByUserId(int userId);
    void deleteFavorite(int id);
}
