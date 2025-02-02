package spring.shopapp.services.favorite;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import spring.shopapp.dtos.response.FavoriteResponse;
import spring.shopapp.exception.AppException;
import spring.shopapp.exception.ErrorCode;
import spring.shopapp.mapper.FavoriteMapper;
import spring.shopapp.models.Favorite;
import spring.shopapp.models.Product;
import spring.shopapp.models.User;
import spring.shopapp.repositories.FavoriteRepository;
import spring.shopapp.repositories.ProductRepository;
import spring.shopapp.repositories.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FavoriteServiceImpl implements FavoriteService {
    UserRepository userRepository;
    ProductRepository productRepository;
    FavoriteRepository favoriteRepository;
    FavoriteMapper favoriteMapper;


    @Override
    public FavoriteResponse addFavorite(int userId, int productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->  new AppException(ErrorCode.USER_NOT_FOUND));
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        Favorite favorite = favoriteMapper.toFavorite(user, product);
        return favoriteMapper.toFavoriteResponse(favoriteRepository.save(favorite));
    }

    @Override
    public List<FavoriteResponse> getFavorites() {
        return favoriteRepository.findAll().stream().map(favoriteMapper::toFavoriteResponse)
                .toList();
    }

    @Override
    public void deleteFavorite(int id) {

    }
}
