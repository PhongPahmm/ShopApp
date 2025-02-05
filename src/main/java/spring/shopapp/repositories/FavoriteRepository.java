package spring.shopapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.shopapp.models.Favorite;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    boolean existsByUserIdAndProductId(int userId, int productId);
    List<Favorite> findAllByUserId(int userId);
    void deleteByUserIdAndProductId(int userId, int productId);
}
