package spring.shopapp.services.favorite;

import spring.shopapp.dtos.response.FavoriteResponse;

import java.util.List;

public interface FavoriteService {
    FavoriteResponse addFavorite(int userId, int productId);
    List<FavoriteResponse> getFavorites();
    void deleteFavorite(int id);
}
