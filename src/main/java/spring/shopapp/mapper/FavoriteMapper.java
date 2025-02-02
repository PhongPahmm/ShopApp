package spring.shopapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spring.shopapp.dtos.response.FavoriteResponse;
import spring.shopapp.models.Favorite;
import spring.shopapp.models.Product;
import spring.shopapp.models.User;

@Mapper(componentModel = "spring")
public interface FavoriteMapper {
    @Mapping(target = "id", ignore = true)
    Favorite toFavorite(User user, Product product);
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "product.id", target = "productId")
    FavoriteResponse toFavoriteResponse(Favorite favorite);
}
