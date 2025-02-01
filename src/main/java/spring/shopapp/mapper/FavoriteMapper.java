package spring.shopapp.mapper;

import org.mapstruct.Mapper;
import spring.shopapp.dtos.request.FavoriteAddRequest;
import spring.shopapp.dtos.response.FavoriteResponse;
import spring.shopapp.models.Favorite;

@Mapper(componentModel = "spring")
public interface FavoriteMapper {
    Favorite toFavorite(FavoriteAddRequest request);
    FavoriteResponse toFavoriteResponse(Favorite favorite);
}
