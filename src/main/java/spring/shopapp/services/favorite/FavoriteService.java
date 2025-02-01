package spring.shopapp.services.favorite;

import spring.shopapp.dtos.request.FavoriteAddRequest;
import spring.shopapp.dtos.response.FavoriteResponse;

import java.util.List;

public interface FavoriteService {
    FavoriteResponse addFavorite(FavoriteAddRequest request);
    List<FavoriteResponse> getFavorites();
    void deleteFavorite(int id);
}
