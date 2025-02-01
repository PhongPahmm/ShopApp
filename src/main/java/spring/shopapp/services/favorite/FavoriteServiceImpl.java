package spring.shopapp.services.favorite;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import spring.shopapp.dtos.request.FavoriteAddRequest;
import spring.shopapp.dtos.response.FavoriteResponse;
import spring.shopapp.mapper.FavoriteMapper;
import spring.shopapp.models.Favorite;
import spring.shopapp.repositories.FavoriteRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FavoriteServiceImpl implements FavoriteService {
    FavoriteRepository favoriteRepository;
    FavoriteMapper favoriteMapper;


    @Override
    public FavoriteResponse addFavorite(FavoriteAddRequest request) {
        Favorite favorite = favoriteMapper.toFavorite(request);
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
